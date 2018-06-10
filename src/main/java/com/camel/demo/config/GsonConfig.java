package com.camel.demo.config;

import org.apache.camel.component.gson.GsonDataFormat;
import org.springframework.context.annotation.Configuration;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

@Configuration
public class GsonConfig {
    public GsonDataFormat writeConfig() {
        GsonDataFormat format  = new GsonDataFormat();
        format.setFieldNamingStrategy(LOWER_CASE_WITH_UNDERSCORES);
        return format;
    }
}
