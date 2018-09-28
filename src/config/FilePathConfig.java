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

    public void initWyPath() {
        remoteApiPath = "/remote/api/";
        remoteApiImplPath = "/remote/api/impl/";
        controllerPath = "/controller/";
        servicePath = "/interfaces/";
        serviceImplPath = "/service/";
        daoPath = "/dao/";
        mapperPath = "/dao/";
        xmlPath = "/xml/";
        entityPath = "/model/entity/";
        enhancedPath = "/model/entity/enhanced/";
        enumPackagePath = "/enumcode/";
        responseBeanPackagePath = "/model/response/";
        requestBeanPackagePath = "/model/request/";
    }

    public void initSolutionPath() {
        remoteApiPath = String.format("/remote/api/");
        remoteApiImplPath = String.format("/remote/api/impl/");
        controllerPath = String.format("/controller/");
        servicePath = String.format("/interfaces/");
        serviceImplPath = String.format("/service/");
        daoPath = String.format("/persistence/dao/ ");
        mapperPath = String.format("/persistence/mapper/");
        xmlPath = String.format("/xml/");
        entityPath = String.format("/bean/po/");
        enhancedPath = String.format("/bean/enhanced/");
        enumPackagePath = String.format("/bean/enums/");
        responseBeanPackagePath = String.format("/bean/resp/");
        requestBeanPackagePath = String.format("/bean/req/");
    }
}
