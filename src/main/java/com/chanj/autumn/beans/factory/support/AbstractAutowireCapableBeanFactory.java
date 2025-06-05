package com.chanj.autumn.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.PropertyValue;
import com.chanj.autumn.beans.PropertyValues;
import com.chanj.autumn.beans.factory.*;
import com.chanj.autumn.beans.factory.config.AutowireCapableBeanFactory;
import com.chanj.autumn.beans.factory.config.BeanDefinition;
import com.chanj.autumn.beans.factory.config.BeanPostProcessor;
import com.chanj.autumn.beans.factory.config.BeanReference;
import cn.hutool.core.bean.BeanUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    InstaniationStrategy instaniationStrategy = new CglibSubclassingInstaniationStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean  = createBeanInstance(beanDefinition, beanName, args);
            applyPropertyValues(beanName, bean, beanDefinition);

            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        addSingleton(beanName, bean);
        return bean;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Constructor constructorToUse = null;
        Class<?> beanClass   = beanDefinition.getBeanClass();
        Constructor<?>[] constructors = beanClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if(args != null && constructor.getParameterTypes().length == args.length) {
                constructorToUse = constructor;
                break;
            }
        }

        return getInstaniationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstaniationStrategy getInstaniationStrategy() {
        return instaniationStrategy;
    }

    public void setInstaniationStrategy(InstaniationStrategy instaniationStrategy) {
        this.instaniationStrategy = instaniationStrategy;
    }

    public void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue propertyValue : propertyValues.getPropertyValues()){
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if(value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                BeanUtil.setFieldValue(bean, name, value);
//                BeanUtils.setProperty(bean, beanName, value);
            }
        }catch (BeansException e) {
            throw new RuntimeException(e);
        }
    };

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
        if(bean instanceof BeanClassLoaderAware){
            ((BeanClassLoaderAware)bean).setBeanClassLoader(getBeanClassLoader());
        }
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware)bean).setBeanFactory(this);
        }
        if(bean instanceof BeanNameAware){
            ((BeanNameAware)bean).setBeanName(beanName);
        }

        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);

        try {
            invokeInitmethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);
        return wrappedBean;
    }

    //TODO
    private void invokeInitmethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) throws Exception {
        // 用于实现了InitializingBean接口方法的初始化实现
        if(wrappedBean instanceof InitializingBean){
            ((InitializingBean) wrappedBean).afterPropertiesSet();;
        }

        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);

            if(initMethod == null){
                throw new NoSuchMethodException(initMethodName);
            }
            initMethod.invoke(wrappedBean);
        }
    }
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for(BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            Object current= beanPostProcessor.postProcessorAfterInitialization(result, beanName);
            if(null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for(BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            Object current= beanPostProcessor.postProcessorBeforeInitialization(result, beanName);
            if(null == current) return result;
            result = current;
        }
        return result;
    }
}
