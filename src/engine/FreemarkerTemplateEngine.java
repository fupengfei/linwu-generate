package engine;

import bean.FileBuilder;
import bean.FileChoose;
import bean.Table;
import cache.Cache;
import com.google.common.base.CaseFormat;
import config.FilePathConfig;
import config.GlobalConfig;
import config.TemplateConfig;
import contants.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import utils.IOUtils;

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
            //文件名
            createFile(builder,templateConfig.getXml(),format);
        }

        if(fileChoose.isEntity()){
            String format = String.format("%s%s%s%s%s", outputDir, "/",filePathConfig.getEntityPath(), table.getFile(),".java");
            //文件名
            createFile(builder,templateConfig.getEntity(),format);
        }
        if(fileChoose.isEnhanced()){
            String format = String.format("%s%s%s%s%s", outputDir, "/",filePathConfig.getEnhancedPath(), table.getFile()+"Enhanced",".java");
            //文件名
            createFile(builder,templateConfig.getEnhanced(),format);
        }


        if(fileChoose.isReq()){}
        if(fileChoose.isResp()){}
        if(fileChoose.isQuery()){}
        open(outputDir);
    }

    public static void createFile(FileBuilder fileBuilder,String templatePath,String outputFile) throws IOException, TemplateException {
        makeDir(new File(outputFile));
        Template template = configuration.getTemplate(templatePath);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile));
        template.process(fileBuilder, new OutputStreamWriter(fileOutputStream, Constant.UTF8));
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
