package engine;

import bean.Table;
import cache.Cache;
import com.google.common.base.CaseFormat;
import config.GlobalConfig;
import contants.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;
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

    public static void writer(Table table) throws Exception {
        GlobalConfig global = (GlobalConfig) Cache.getGuavaTable().get(Constant.GLOBAL_CONFIG, Constant.GLOBAL_CONFIG);
        String projectTemplate = global.getProjectTemplate();
        String path = FreemarkerTemplateEngine.class.getResource("/").getPath();
        path = String.format("%s%s",path, "resource/template/");
        //方案管
        if(Constant.SOLUTION.equals(projectTemplate)){
            path=path+"solution/";
        }
        //众包
        if(Constant.WY.equals(projectTemplate)){
            path=path+"od_sy/";
        }



//        String controllerFileName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName())+Constant.Controller;
        String entityFileName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName());
        createFile(table,,global.getOutputDir()+);


    }

    public void createFile(Object obj,String templatePath,String outputFile) throws Exception {
        Template template = configuration.getTemplate(templatePath);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile));
        template.process(obj, new OutputStreamWriter(fileOutputStream, Constant.UTF8));
        fileOutputStream.close();
    }

    public void makeDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            makeDir(file.getParentFile());
            file.mkdir();
        }
    }

    public static void main(String[] args) {
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
    }
}
