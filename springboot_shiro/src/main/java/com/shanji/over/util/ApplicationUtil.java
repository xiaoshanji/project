package com.shanji.over.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @version: V1.0
 * @className: ApplicationUtil
 * @packageName: com.shanji.over.util
 * @data: 2020/11/3 20:34
 * @description:
 */
@Component
public class ApplicationUtil implements ApplicationContextAware
{
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        context = applicationContext;
    }

    public static Object getBeanByName(String name)
    {
        return context.getBean(name);
    }
}
