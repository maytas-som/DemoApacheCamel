package com.camel.demo.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
public class CamelRouteConfig extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")

                //Enable swagger endpoint.
                .apiContextPath("/swagger") //swagger endpoint path
                .apiContextRouteId("swagger") //id of route providing the swagger endpoint

                //Swagger properties
                .contextPath("/camel-demo/api") //base.path swagger property; use the mapping path set for CamelServlet
                .apiProperty("spring.title", "Swagger UI with Apache Camel-servlet")
                .apiProperty("spring.version", "1.0")
                .apiProperty("spring.contact.name", "Maytas Somsmai")
                .apiProperty("spring.contact.email", "maytas.som@gmail.com")
                .apiProperty("spring.contact.url", "https://github.com/maytas-som/DemoApacheCamel")
                .apiProperty("host", "") //by default 0.0.0.0
                .apiProperty("port", "8080")
                .apiProperty("schemes", "")
        ;
    }

    @Controller
    public class SwaggerConfig {
        @RequestMapping("/swagger-ui")
        public String redirectToUi() {
            return "redirect:/webjars/swagger-ui/index.html?url=/camel-demo/api/swagger&validatorUrl=";
        }
    }
}