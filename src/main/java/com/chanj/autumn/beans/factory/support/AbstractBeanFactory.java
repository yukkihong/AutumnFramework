package com.chanj.autumn.beans.factory.support;

import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.factory.BeanFactory;
import com.chanj.autumn.beans.factory.config.BeanDefinition;
import com.chanj.autumn.beans.factory.config.BeanFactoryPostProcessor;
import com.chanj.autumn.beans.factory.config.BeanPostProcessor;
import com.chanj.autumn.beans.factory.config.ConfigurableBeanFactory;
import com.chanj.autumn.utils.Classutils;
import org.junit.platform.commons.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostprocessors = new ArrayList<BeanPostProcessor>();

    private ClassLoader beanClassLoader = Classutils.getDefaultClassLoader();

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }

    @Override
    public Object getBean(String name) throws BeansException {
      return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
       return  doGetBean(name , args);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostprocessors.remove(beanPostProcessor);
        this.beanPostprocessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostprocessors;
    }

    protected <T> T doGetBean(final String name, final Object[] args){
        Object bean = getSingleton(name);
        if(bean != null) {
            return (T)bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)createBean(name, beanDefinition, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T)getBean(name);
    }
}
