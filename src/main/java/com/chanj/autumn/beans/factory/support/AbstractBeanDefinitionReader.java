package com.chanj.autumn.beans.factory.support;

import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.core.io.DefaultResourceLoader;
import com.chanj.autumn.core.io.Resource;
import com.chanj.autumn.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements  BeanDefinitionReader {
    private ResourceLoader resourceLoader;
    private final  BeanDefinitionRegistry registry;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this(beanDefinitionRegistry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        this.registry = beanDefinitionRegistry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }



}
