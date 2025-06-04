package com.chanj.autumn.beans.factory.support;

import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.core.io.Resource;
import com.chanj.autumn.core.io.ResourceLoader;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String resources) throws BeansException;

    void loadBeanDefintions(String... resources) throws BeansException;
}
