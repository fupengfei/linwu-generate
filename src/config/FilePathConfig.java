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

    private String enumPath;

    private String responseBeanPackagePath;

    private String requestBeanPackagePath;

    public void initSolutionPath() {
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
        enumPath = "/enumcode/";
        responseBeanPackagePath = "/model/response/";
        requestBeanPackagePath = "/model/request/";
    }

    public void initWyPath() {
        remoteApiPath = "/remote/api/";
        remoteApiImplPath =  "/remote/api/impl/" ;
        controllerPath =  "/controller/" ;
        servicePath =  "/interfaces/" ;
        serviceImplPath =  "/service/" ;
        daoPath =  "/persistence/dao/ " ;
        mapperPath =  "/persistence/mapper/" ;
        xmlPath =  "/xml/" ;
        entityPath =  "/bean/po/" ;
        enhancedPath =  "/bean/enhanced/" ;
        enumPath =  "/bean/enums/" ;
        responseBeanPackagePath =  "/api/bean/resp/";
        requestBeanPackagePath =  "/api/bean/req/";
    }
}
