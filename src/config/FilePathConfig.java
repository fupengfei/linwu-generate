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
public class FilePathConfig {

    private String parentPath;

    private String remoteApiPath;

    private String remoteApiImplPath;

    private String controllerPath;

    private String servicePath;

    private String serviceImplPath;

    private String daoPath;

    private String mapperPath;

    private String xmlPath;

    private String entityPath;

    private String enhancedPath;

    private String enumPackagePath;

    private String responseBeanPackagePath;

    private String requestBeanPackagePath;

    public void initWyPath(String parentPackage) {
        remoteApiPath = String.format("%s/remote/api", parentPackage);
        remoteApiImplPath = String.format("%s/remote/api/impl", parentPackage);
        controllerPath = String.format("%s/controller", parentPackage);
        servicePath = String.format("%s/interfaces", parentPackage);
        serviceImplPath = String.format("%s/service", parentPackage);
        daoPath = String.format("%s/dao", parentPackage);
        mapperPath = String.format("%s/dao", parentPackage);
        xmlPath = String.format("%s/xml", parentPackage);
        entityPath = String.format("%s/model/entity", parentPackage);
        enhancedPath = String.format("%s/model/entity/enhanced", parentPackage);
        enumPackagePath = String.format("%s/enumcode", parentPackage);
        responseBeanPackagePath = String.format("%s/model/response", parentPackage);
        requestBeanPackagePath = String.format("%s/model/request", parentPackage);
    }

    public void initSolutionPath(String parentPackage) {
        remoteApiPath = String.format("%s/remote/api", parentPackage);
        remoteApiImplPath = String.format("%s/remote/api/impl", parentPackage);
        controllerPath = String.format("%s/controller", parentPackage);
        servicePath = String.format("%s/interfaces", parentPackage);
        serviceImplPath = String.format("%s/service", parentPackage);
        daoPath = String.format("%s/persistence/dao", parentPackage);
        mapperPath = String.format("%s/persistence/mapper", parentPackage);
        xmlPath = String.format("%s/xml", parentPackage);
        entityPath = String.format("%s/bean/po", parentPackage);
        enhancedPath = String.format("%s/bean/enhanced", parentPackage);
        enumPackagePath = String.format("%s/bean/enums", parentPackage);
        responseBeanPackagePath = String.format("%s/bean/resp", parentPackage);
        requestBeanPackagePath = String.format("%s/bean/req", parentPackage);
    }
}
