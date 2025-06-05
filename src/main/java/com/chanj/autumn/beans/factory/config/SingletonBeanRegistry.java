package com.chanj.autumn.beans.factory.config;

import java.lang.reflect.InvocationTargetException;

public interface SingletonBeanRegistry {
    public Object getSingleton(String beanName);

    void destroySingletons() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
}
