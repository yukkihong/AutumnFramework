package com.chanj.autumn.test;

import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.PropertyValue;
import com.chanj.autumn.beans.PropertyValues;
import com.chanj.autumn.beans.factory.ConfigurableListableBeanFactory;
import com.chanj.autumn.beans.factory.config.BeanDefinition;
import com.chanj.autumn.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
