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

    public static void writer(FileBuilder builder) throws Exception {
        GlobalConfig globalConfig = builder.getGlobalConfig();
        String outputDir = globalConfig.getOutputDir();
        FilePathConfig filePathConfig = globalConfig.getFilePathConfig();
        TemplateConfig templateConfig = globalConfig.getTemplateConfig();

//        cfg.setDirectoryForTemplateLoading(new File("template/od_sy/main"));

        //文件夹是否存在
        Table table = builder.getTable();
        FileChoose fileChoose = table.getFileChoose();
        if(fileChoose.isRemote()){

        }
        if(fileChoose.isRemoteImpl()){}
        if(fileChoose.isController()){}
        if(fileChoose.isService()){}
        if(fileChoose.isServiceImpl()){}
        if(fileChoose.isDao()){}
        if(fileChoose.isMapper()){}
        if(fileChoose.isXml()){
            String format = null;
            if(Constant.SOLUTION.equals(globalConfig.getSource())){
                 format = String.format("%s%s%s%s%s", outputDir, "/",filePathConfig.getXmlPath(), table.getFile()+"Mapper",".xml");
            }
            if(Constant.WY.equals(globalConfig.getSource())){
                 format = String.format("%s%s%s%s%s", outputDir, "/",filePathConfig.getXmlPath(), table.getName()+"_mapper",".xml");
            }
            createFile(builder,templateConfig.getXml(),format);
        }

        if(fileChoose.isEntity()){
            String format = String.format("%s%s%s%s%s", outputDir, "/",filePathConfig.getEntityPath(), table.getFile(),".java");
            createFile(builder,templateConfig.getEntity(),format);
        }
        if(fileChoose.isEnhanced()){
            String format = String.format("%s%s%s%s%s", outputDir, "/",filePathConfig.getEnhancedPath(), table.getFile()+"Enhanced",".java");
            createFile(builder,templateConfig.getEnhanced(),format);
        }

        if(fileChoose.isReq()){}
        if(fileChoose.isResp()){}
        if(fileChoose.isQuery()){}

        //生成字段关联对象枚举
        List<TableField> fieldInfoList = table.getFieldInfoList();
        fieldInfoList.removeIf(field->field.getEnumBean()==null);
        for (TableField field : fieldInfoList) {
            String bizEnum = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getTableName()).toLowerCase();
            String format = String.format("%s%s%s%s%s", outputDir, "/",filePathConfig.getEnumPath()+bizEnum+"/", field.getEnumBean().getClassName(),".java");
            Map<String, Object> map = new HashMap<>();
            map.put("globalConfig",globalConfig);
            map.put("enumBean",field.getEnumBean());
            map.put("bizEnum",bizEnum);
            createFile(map,templateConfig.getEnumTemplate(),format);
        }
        open(outputDir);
    }

    public static void createFile(Object obj,String templatePath,String outputFile) throws IOException, TemplateException {
        makeDir(new File(outputFile));
        Template template = configuration.getTemplate(templatePath);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile));
        template.process(obj, new OutputStreamWriter(fileOutputStream, Constant.UTF8));
        fileOutputStream.close();
    }


    public static void makeDir(File file) throws IOException {
        File parentFile = file.getParentFile();
        boolean exists = parentFile.exists();
        if(!exists){
            parentFile.mkdirs();
        }
    }

    public static void open(String outDir) {
            try {
                String osName = System.getProperty("os.name");
                if (osName != null) {
                    if (osName.contains("Mac")) {
                        Runtime.getRuntime().exec("open " + outDir);
                    } else if (osName.contains("Windows")) {
                        Runtime.getRuntime().exec("cmd /c start " + outDir);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
