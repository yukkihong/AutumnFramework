package com.chanj.autumn.beans.factory.support;

import com.chanj.autumn.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstaniationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object... args){
        Class clazz = beanDefinition.getBeanClass();

        try {
            if(ctor== null){
                return clazz.getDeclaredConstructor().newInstance();
            }else{
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
