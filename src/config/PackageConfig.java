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
}
