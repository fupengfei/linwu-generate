package engine;

import contants.Constant;
import freemarker.template.Configuration;

public class FreemarkerTemplateEngine {
    private Configuration configuration;

    public FreemarkerTemplateEngine init() {
        configuration = new Configuration();
        configuration.setDefaultEncoding(Constant.UTF8);
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, Constant.SLASH);
        return this;
    }

//    public void writer(Generator generator, String templatePath, String outputFile) throws Exception {
//        Template template = configuration.getTemplate(templatePath);
//        InputStream in = ClassUtils.class.getClassLoader()
//                .getResourceAsStream(String.format("templates/%s", templatePath));
//        ByteArrayOutputStream out = IOUtils.convent(in);
//
//        template.process(generator, new OutputStreamWriter(out, Constant.UTF8));
//
//        in.close();
//        out.close();
//        log.info("模板:" + templatePath + ";  文件:" + outputFile);
//    }
}
