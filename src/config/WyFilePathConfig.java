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
public class WyFilePathConfig  extends FilePath{
    public void setAllPath(String parentPackage){
        remoteApiPath = String.format("%s/remote/api",parentPackage);
        remoteApiImplPath = String.format("%s/remote/api/impl",parentPackage);
        controllerPath = String.format("%s/controller",parentPackage);
        servicePath = String.format("%s/service",parentPackage);
        serviceImplPath = String.format("%s/service",parentPackage);
        daoPath = String.format("%s/dao",parentPackage);
        mapperPath = String.format("%s/mapper",parentPackage);
        xmlPath = String.format("%s/xml",parentPackage);
        entityPath = String.format("%s/bean.po",parentPackage);
        enhancedPath = String.format("%s/bean/po/enhanced",parentPackage);
        enumPackagePath = String.format("%s/bean/enums",parentPackage);
        responseBeanPackagePath = String.format("%s/bean/model/req",parentPackage);
        requestBeanPackagePath = String.format("%s/bean/model/resp",parentPackage);
    }
}
