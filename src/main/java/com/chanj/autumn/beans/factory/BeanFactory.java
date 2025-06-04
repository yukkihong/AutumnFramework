package com.chanj.autumn.beans.factory;

import com.chanj.autumn.beans.BeansException;

public interface BeanFactory {
    public Object getBean(String name) throws BeansException;

    public Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
