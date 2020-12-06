package com.sjcarpentry.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "estimate.orders")
@Data
public class DataProperties {

    private int pageSize;
}
