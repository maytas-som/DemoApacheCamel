package com.camel.demo.feature.get.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoGetResponse {
    private String valueFromQueryParam1;
    private String valueFromQueryParam2;
}
