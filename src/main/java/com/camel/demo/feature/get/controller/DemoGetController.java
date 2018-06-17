package com.camel.demo.feature.get.controller;

import com.camel.demo.config.GsonConfig;
import com.camel.demo.feature.get.service.DemoGetService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoGetController extends RouteBuilder {

    private DemoGetService demoGetService;
    private GsonConfig gsonConfig;

    @Autowired
    public DemoGetController(DemoGetService demoGetService, GsonConfig gsonConfig) {
        this.demoGetService = demoGetService;
        this.gsonConfig = gsonConfig;
    }

    @Override
    public void configure() throws Exception {
        rest().tag("Demo API")
                .get("/get").description("Demo GET API.")
                .param().name("param_1").type(RestParamType.query)
                        .dataType("String").endParam()
                .param().name("param_2").type(RestParamType.query)
                        .dataType("String").endParam()
                .route()
                .process(demoGetService)
                .marshal(gsonConfig.writeConfig())
                .endRest();
    }
}