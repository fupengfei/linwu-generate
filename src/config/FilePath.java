package config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ：ZhangLei
 * @date ：2018/9/26
 * @description :
 */
@Getter
@Setter
public class FilePath {
    protected String parentPath;

    protected String remoteApiPath;

    protected String remoteApiImplPath;

    protected String controllerPath;

    protected String servicePath;

    protected String serviceImplPath;

    protected String daoPath;

    protected String mapperPath;

    protected String xmlPath;

    protected String entityPath;

    protected String enhancedPath;

    protected String enumPackagePath;

    protected String responseBeanPackagePath;

    protected String requestBeanPackagePath;
}
