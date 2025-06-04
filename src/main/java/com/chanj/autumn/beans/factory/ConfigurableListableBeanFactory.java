package com.chanj.autumn.beans.factory;

import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.factory.config.AutowireCapableBeanFactory;
import com.chanj.autumn.beans.factory.config.BeanDefinition;
import com.chanj.autumn.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory , ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
