package config;


import lombok.Getter;
import lombok.Setter;

/**
 * @author ：ZhangLei
 * @date ：2018/8/31
 * @description :
 */
@Getter
@Setter
public class FileNameConfig {
    /**
     * 远程API
     */
    private String remoteApiName;
    /**
     * 远程API实现
     */
    private String remoteApiImplName;
    /**
     *controller
     */
    private String controllerName;
    /**
     * service
     */
    private String serviceName;
    /**
     * serviceImpl
     */
    private String serviceImplName;
    /**
     * Dao
     */
    private String daoName;
    /**
     * Mapper
     */
    private String mapperName;
    /**
     * XML
     */
    private String xmlName;
    /**
     * 实体类名
     */
    private String entityName;
    /**
     * 枚举名称
     */
    private String enumName;
    /**
     * PO增强类
     */
    private String enhancedName;
    /**
     * Resp
     */
    private String ResponseBeanName;

    /**
     * req
     */
    private String requestBeanName;
}
