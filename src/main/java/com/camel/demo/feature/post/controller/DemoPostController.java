package com.camel.demo.feature.post.controller;

import com.camel.demo.config.GsonConfig;
import com.camel.demo.feature.post.domain.DemoPostRequest;
import com.camel.demo.feature.post.service.DemoPostService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoPostController extends RouteBuilder {

    private DemoPostService demoPostService;
    private GsonConfig gsonConfig;

    @Autowired
    public DemoPostController(DemoPostService demoPostService, GsonConfig gsonConfig) {
        this.demoPostService = demoPostService;
        this.gsonConfig = gsonConfig;
    }

    @Override
    public void configure() throws Exception {
        rest("/api")
                .post("/post")
                .param().name("param_1").type(RestParamType.query).arrayType("String").required(false).endParam()
                .type(DemoPostRequest.class)
                .route()
                .unmarshal().json(JsonLibrary.Gson, DemoPostRequest.class)
                .process(demoPostService)
                .marshal(gsonConfig.writeConfig())
                .endRest();
    }
}