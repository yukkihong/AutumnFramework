package com.chanj.autumn.test;

import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.factory.config.BeanPostProcessor;
import com.chanj.autumn.test.Bean.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：广州");
        }
        return bean;
    }

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：广州");
        }
        return bean;
    }
}
