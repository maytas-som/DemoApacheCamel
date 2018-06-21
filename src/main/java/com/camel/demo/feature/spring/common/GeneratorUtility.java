package com.camel.demo.feature.spring.common;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class GeneratorUtility {
    private GeneratorUtility () {}

    public static String generateTransactionId() {
        // Pick from ThreadContxt
        String transactionId = ThreadContext.get("transactionID");
        // If null or empty, generate new one
        if (StringUtils.isEmpty(transactionId)) {
            return convertString(UUID.randomUUID().toString());
        } else {
            return convertString(transactionId);
        }
    }

    private static String convertString(String transactionId){
        String[] transactionSplited = transactionId.split("-");
        if(transactionSplited.length == 1){
            transactionId = "demo-"+ transactionSplited[0];
        }
        return transactionId;
    }
}
