package config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalConfig {
    /**
     * 生成文件的输出目录【默认 D 盘根目录】
     */
    private String outputDir;
    /**
     * 开发人员
     */
    private String author;

    /**
     * 模块
     */
    private String parentPackage;


    private String projectTemplate;

    private PackageConfig packageConfig;

    private FilePathConfig filePathConfig;

    private TemplateConfig templateConfig;

}
