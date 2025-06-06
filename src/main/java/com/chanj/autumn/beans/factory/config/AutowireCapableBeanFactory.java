package com.chanj.autumn.beans.factory.config;

import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;


    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)throws BeansException;
}
