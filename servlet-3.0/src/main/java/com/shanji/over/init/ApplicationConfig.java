package com.shanji.over.init;


import com.shanji.over.config.RootConfig;
import com.shanji.over.config.WebConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @version: V1.0
 * @className: WebApplicationConfig
 * @packageName: com.shanji.over.config
 * @data: 2020/12/12 15:26
 * @description:
 */

public class ApplicationConfig extends AbstractAnnotationConfigDispatcherServletInitializer
{

    // 加载spring容器，相当于applicationContext.xml
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    // 加载servletContext，相当于spring-mvc.xml
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
