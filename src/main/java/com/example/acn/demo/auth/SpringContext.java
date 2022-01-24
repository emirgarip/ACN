package com.example.acn.demo.auth;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringContext implements ApplicationContextAware {
    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContext.context = applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException
    {
        return context.getBean(requiredType);
    }

}
