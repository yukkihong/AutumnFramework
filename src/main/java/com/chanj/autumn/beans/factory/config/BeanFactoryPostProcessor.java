package com.chanj.autumn.beans.factory.config;

import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
