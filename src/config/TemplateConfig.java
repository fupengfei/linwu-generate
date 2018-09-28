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
public class TemplateConfig {

    private static String path = String
            .format("%s%s", TemplateConfig.class.getResource("/").getPath(), "resource/template/");

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

    private String response;

    private String request;

    public void initWy() {
        path = path + "od_sy";
        getTemplate();
    }



    public void initSolution() {
        path = path + "solution";
        getTemplate();
    }

    private void getTemplate() {
        remoteApi = path + "remoteApi.ftl";
        remoteApiImpl = path + "remoteApiImpl.ftl";
        controller = path + "controller.ftl";
        service = path + "service.ftl";
        serviceImpl = path + "serviceImpl.ftl";
        dao = path + "dao.ftl";
        mapper = path + "mapper.ftl";
        xml = path + "xml.ftl";
        entity = path + "entity.ftl";
        enhanced = path + "enhanced.ftl";
        enumPackage = path + "enumPackage.ftl";
        response = path + "response.ftl";
        request = path + "request.ftl";
    }
}
