package com.camel.demo.feature.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoPostResponse {
    private String valueFromQueryParam;
    private String valueFromRequestBody;
}
