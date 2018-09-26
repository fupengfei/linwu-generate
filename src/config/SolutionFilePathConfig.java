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
public class SolutionFilePathConfig extends FilePath{

    public void setAllPath(String parentPackage){
        remoteApiPath = String.format("%s/remote/api",parentPackage);
        remoteApiImplPath = String.format("%s/remote/api/impl",parentPackage);
        controllerPath = String.format("%s/controller",parentPackage);
        servicePath = String.format("%s/service",parentPackage);
        serviceImplPath = String.format("%s/service",parentPackage);
        daoPath = String.format("%s/dao",parentPackage);
        mapperPath = String.format("%s/dao",parentPackage);
        xmlPath = String.format("%s/xml",parentPackage);
        entityPath = String.format("%s/model/entity",parentPackage);
        enhancedPath = String.format("%s/model/entity/enhanced",parentPackage);
        enumPackagePath = String.format("%s/enumcode",parentPackage);
        responseBeanPackagePath = String.format("%s/model/response",parentPackage);
        requestBeanPackagePath = String.format("%s/model/request",parentPackage);
    }
}
