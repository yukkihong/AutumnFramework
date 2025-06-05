package com.chanj.autumn.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.factory.DisposableBean;
import com.chanj.autumn.beans.factory.config.BeanDefinition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {
    private String beanName;
    private Object bean;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        if(StrUtil.isNotBlank(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if(null == destroyMethodName) {
                throw new BeansException("Can not destroy bean " + beanName);
            }
            destroyMethod.invoke(bean);
        }
    }
}
