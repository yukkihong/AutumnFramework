package com.chanj.autumn.context.support;

import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.factory.config.BeanPostProcessor;
import com.chanj.autumn.context.ApplicationContext;
import com.chanj.autumn.context.ApplicationContextAware;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private  final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware)bean).setApplicationContext(applicationContext);
        }
        return null;
    }

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
