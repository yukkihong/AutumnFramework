package com.chanj.autumn.beans.factory.support;

import com.chanj.autumn.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public interface InstaniationStrategy {
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object... args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}

