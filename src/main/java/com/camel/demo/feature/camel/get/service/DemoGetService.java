package com.camel.demo.feature.camel.get.service;

import com.camel.demo.feature.camel.get.domain.DemoGetResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class DemoGetService implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getOut().setBody(
                DemoGetResponse.builder()
                        .valueFromQueryParam1(
                                exchange.getIn().getHeader("param_1", String.class)
                        )
                        .valueFromQueryParam2(
                                exchange.getIn().getHeader("param_2", String.class)
                        )
                        .build()
        );
    }
}
