package com.camel.demo.feature.camel.post.service;

import com.camel.demo.feature.camel.post.domain.DemoPostResponse;
import com.camel.demo.feature.camel.post.domain.DemoPostRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class DemoPostService implements Processor {

    Logger log = LogManager.getLogger(DemoPostService.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getOut().setBody(
                DemoPostResponse.builder()
                        .valueFromQueryParam(
                                exchange.getIn().getHeader("param_1", String.class)
                        )
                        .valueFromRequestBody(
                                exchange.getIn().getBody(DemoPostRequest.class).getRequestValue()
                        )
                        .build()
        );
    }
}
