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
public class WyPackageConfig extends PackageConfig{
    public void setAll(String parentPackage){
        remoteApi = String.format("%s.remote.api",parentPackage);
        remoteApiImpl = String.format("%s.remote.api.impl",parentPackage);
        controller = String.format("%s.controller",parentPackage);
        service = String.format("%s.service",parentPackage);
        serviceImpl = String.format("%s.service",parentPackage);
        dao = String.format("%s.dao",parentPackage);
        mapper = String.format("%s.mapper",parentPackage);
        xml = String.format("%s.xml",parentPackage);
        entity = String.format("%s.bean.po",parentPackage);
        enhanced = String.format("%s.bean.po.enhanced",parentPackage);
        enumPackage = String.format("%s.bean.enums",parentPackage);
        responseBeanPackage = String.format("%s.bean.model.req",parentPackage);
        requestBeanPackage = String.format("%s.bean.model.resp",parentPackage);
    }
}
