package com.tu.xinghao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: lixinghao
 * @date: 2019-10-16 20:43
 * @Description:
 */
public class ApplicationContextFactory {
    private static ApplicationContext applicationContext;

    static {
        applicationContext = new AnnotationConfigApplicationContext("com.tu.xinghao");
    }

    public static <T> Object getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
