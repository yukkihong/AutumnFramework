package com.chanj.autumn.beans.factory.support;

import com.chanj.autumn.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    public boolean containsBeanDefinition(String beanName);
}
