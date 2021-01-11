package com.changjianghuyu.qixia.web.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SpringContextBeanUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


    public static <T> T getBeanByClass(Class<T> c){
        return context.getBean(c);
    }
    public static Object getBeanByName(String name){
        return context.getBean(name);
    }
}