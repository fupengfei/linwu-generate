package config;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 模板路径配置项
 * </p>
 *
 * @author tzg hubin
 * @since 2017-06-17
 */
@Getter
@Setter
public class TemplateConfig {
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

    private String enumTemplate;

    private String responseBean;

    private String requestBean;
}
