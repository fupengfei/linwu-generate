package config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PackageConfig {
    private String parent;

    private String remoteApi;

    private String remoteApiImpl;

    private String controller;

    private String service;

    private String serviceImpl;

    private String dao;

    private String mapper;

    private String xml;

    private String entity;

    private String enhanced;

    private String enumPackage;

    private String responseBeanPackage;

    private String requestBeanPackage;

    public void initSolution(String parentPackage){
        remoteApi= String.format("%s.remote.api",parentPackage);
        remoteApiImpl = String.format("%s.remote.api.impl",parentPackage);
        controller = String.format("%s.controller",parentPackage);
        service = String.format("%s.interfaces",parentPackage);
        serviceImpl = String.format("%s.service",parentPackage);
//        dao = String.format("%s.dao",parentPackage);
        mapper = String.format("%s.dao",parentPackage);
        xml = String.format("%s.xml",parentPackage);
        entity = String.format("%s.model.entity",parentPackage);
//        enhanced = String.format("%s.model.entity.enhanced",parentPackage);
        enumPackage = String.format("%s.enumcode",parentPackage);
        responseBeanPackage = String.format("%s.model.response",parentPackage);
        requestBeanPackage = String.format("%s.model.request",parentPackage);
    }

    public void initWy(String parentPackage){
        remoteApi = String.format("%s.remote.api",parentPackage);
        remoteApiImpl = String.format("%s.remote.api.impl",parentPackage);
        controller = String.format("%s.controller",parentPackage);
        service = String.format("%s.interfaces",parentPackage);
        serviceImpl = String.format("%s.service",parentPackage);
        dao = String.format("%s.persistence.dao",parentPackage);
        mapper = String.format("%s.persistence.mapper",parentPackage);
        xml = String.format("%s.xml",parentPackage);
        entity = String.format("%s.bean.po",parentPackage);
        enhanced = String.format("%s.bean.po.enhanced",parentPackage);
        enumPackage = String.format("%s.bean.enums",parentPackage);
        responseBeanPackage = String.format("%s.api.bean.model.resp",parentPackage);
        requestBeanPackage = String.format("%s.api.bean.model.req",parentPackage);
    }
}
