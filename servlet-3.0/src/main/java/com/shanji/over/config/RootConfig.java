package com.shanji.over.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @version: V1.0
 * @className: RootConfig
 * @packageName: com.shanji.over.config
 * @data: 2020/12/12 15:27
 * @description:
 */
@Configuration
@ComponentScan(basePackages = "com.shanji.over",excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class RootConfig {
}
