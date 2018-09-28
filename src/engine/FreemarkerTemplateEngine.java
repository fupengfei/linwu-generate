package engine;

import bean.FileBuilder;
import bean.FileChoose;
import bean.Table;
import cache.Cache;
import com.google.common.base.CaseFormat;
import config.GlobalConfig;
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
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, Constant.SLASH);
    }

    public static void writer(FileBuilder builder) throws Exception {
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
        if(fileChoose.isXml()){}
        if(fileChoose.isEntity()){}
        if(fileChoose.isEnhanced()){}


        if(fileChoose.isReq()){}
        if(fileChoose.isResp()){}
        if(fileChoose.isQuery()){}

    }

    public static void createFile(FileBuilder fileBuilder,String templatePath,String outputFile) throws IOException, TemplateException {
        Template template = configuration.getTemplate(templatePath);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile));
        template.process(fileBuilder, new OutputStreamWriter(fileOutputStream, Constant.UTF8));
        fileOutputStream.close();
    }


    public static void makeDir(File file) throws IOException {
        File parentFile = file.getParentFile();
        boolean exists = parentFile.exists();
        if(!exists){
            parentFile.mkdir();
            makeDir(file.getParentFile());
            file.createNewFile();
        }
    }
}
