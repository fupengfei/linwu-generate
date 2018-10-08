package engine;

import bean.FileBuilder;
import bean.FileChoose;
import bean.Table;
import bean.TableField;
import com.google.common.base.CaseFormat;
import config.FilePathConfig;
import config.GlobalConfig;
import config.TemplateConfig;
import contants.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import utils.SystemUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreemarkerTemplateEngine {
    private static Configuration configuration;
    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding(Constant.UTF8);
        try {
            configuration.setDirectoryForTemplateLoading(new File("template/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**需要生成的文件模版
     * @param builder
     * @throws Exception
     */
    public static void writer(FileBuilder builder) throws Exception {
        GlobalConfig globalConfig = builder.getGlobalConfig();
        String outputDir = globalConfig.getOutputDir();
        outputDir = outputDir.replaceAll("\'",File.separator);
        FilePathConfig filePathConfig = globalConfig.getFilePathConfig();
        TemplateConfig templateConfig = globalConfig.getTemplateConfig();

        Table table = builder.getTable();
        FileChoose fileChoose = table.getFileChoose();
        if(fileChoose.isRemote()){

        }
        if(fileChoose.isRemoteImpl()){

        }
        if(fileChoose.isController()){

        }
        if(fileChoose.isService()){

        }
        if(fileChoose.isServiceImpl()){
            String format = null;
            if(Constant.SOLUTION.equals(globalConfig.getSource())){
                format = String.format("%s%s%s%s%s", outputDir, File.separator,filePathConfig.getServiceImplPath(), table.getClassName()+"Service",".java");
            }
            if(Constant.WY.equals(globalConfig.getSource())){
                format = String.format("%s%s%s%s%s", outputDir, File.separator,filePathConfig.getServiceImplPath(), table.getClassName()+"Service",".java");
            }
            createFile(builder,templateConfig.getServiceImpl(),format);
        }
        if(fileChoose.isDao()){
            if(Constant.WY.equals(globalConfig.getSource())){
                String format = String.format("%s%s%s%s%s", outputDir, File.separator,filePathConfig.getDaoPath(), table.getClassName()+"Dao",".java");
                createFile(builder,templateConfig.getDao(),format);
            }
        }
        if(fileChoose.isMapper()){
            String format = null;
            if(Constant.SOLUTION.equals(globalConfig.getSource())){
                format = String.format("%s%s%s%s%s", outputDir, File.separator,filePathConfig.getMapperPath(), "I"+table.getClassName()+"Mapper",".java");
            }
            if(Constant.WY.equals(globalConfig.getSource())){
                format = String.format("%s%s%s%s%s", outputDir, File.separator,filePathConfig.getMapperPath(), "I"+table.getClassName()+"Mapper",".java");
            }
            createFile(builder,templateConfig.getMapper(),format);
        }
        if(fileChoose.isXml()){
            String format = null;
            if(Constant.SOLUTION.equals(globalConfig.getSource())){
                 format = String.format("%s%s%s%s%s", outputDir, File.separator,filePathConfig.getXmlPath(), table.getClassName()+"Mapper",".xml");
            }
            if(Constant.WY.equals(globalConfig.getSource())){
                 format = String.format("%s%s%s%s%s", outputDir, File.separator,filePathConfig.getXmlPath(), table.getName()+"_mapper",".xml");
            }
            createFile(builder,templateConfig.getXml(),format);
        }

        if(fileChoose.isEntity()){
            String format = String.format("%s%s%s%s%s", outputDir, File.separator,filePathConfig.getEntityPath(), table.getClassName(),".java");
            createFile(builder,templateConfig.getEntity(),format);
        }
        if(fileChoose.isEnhanced()){
            String format = String.format("%s%s%s%s%s", outputDir, File.separator,filePathConfig.getEnhancedPath(), table.getClassName()+Constant.ENHANCED,".java");
            createFile(builder,templateConfig.getEnhanced(),format);
        }

        if(fileChoose.isReq()){}
        if(fileChoose.isResp()){}
        if(fileChoose.isQuery()){}

        //生成字段关联对象枚举
        List<TableField> fieldInfoList = table.getFieldInfoList();
        fieldInfoList.removeIf(field->field.getEnumBean()==null);
        for (TableField field : fieldInfoList) {
            //业务枚举的包名
            String bizEnum = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getTableName()).toLowerCase();
            String format = String.format("%s%s%s%s%s", outputDir, File.separator,filePathConfig.getEnumPath()+bizEnum+File.separator, field.getEnumBean().getClassName(),".java");
            Map<String, Object> map = new HashMap<>();
            map.put("globalConfig",globalConfig);
            map.put("enumBean",field.getEnumBean());
            map.put("bizEnum",bizEnum);
            createFile(map,templateConfig.getEnumTemplate(),format);
        }
        SystemUtils.open(outputDir);
    }

    /**填充模版生成文件
     * @param obj
     * @param templatePath
     * @param outputFile
     * @throws IOException
     * @throws TemplateException
     */
    public static void createFile(Object obj,String templatePath,String outputFile) throws IOException, TemplateException {
        makeDir(new File(outputFile));
        Template template = configuration.getTemplate(templatePath);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile));
        template.process(obj, new OutputStreamWriter(fileOutputStream, Constant.UTF8));
        fileOutputStream.close();
    }


    /**生成文件夹
     * @param file
     * @throws IOException
     */
    public static void makeDir(File file){
        File parentFile = file.getParentFile();
        boolean exists = parentFile.exists();
        if(!exists){
            parentFile.mkdirs();
        }
    }

    public static void main(String[] args) {
        String property = System.getProperty("user.dir");
        System.out.println(property);
    }
}
