package config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PackageConfig {
    protected String parent;

    protected String remoteApi;

    protected String remoteApiImpl;

    protected String controller;

    protected String service;

    protected String serviceImpl;

    protected String dao;

    protected String mapper;

    protected String xml;

    protected String entity;

    protected String enhanced;

    protected String enumPackage;

    protected String responseBeanPackage;

    protected String requestBeanPackage;
}
