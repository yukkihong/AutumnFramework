package com.chanj.autumn.beans.factory.config;

import com.chanj.autumn.beans.BeansException;

public interface BeanPostProcessor {
    Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException;
}
