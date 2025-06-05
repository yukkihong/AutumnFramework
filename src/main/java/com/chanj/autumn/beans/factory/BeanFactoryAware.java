package com.chanj.autumn.beans.factory;

import com.chanj.autumn.beans.BeansException;

public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
