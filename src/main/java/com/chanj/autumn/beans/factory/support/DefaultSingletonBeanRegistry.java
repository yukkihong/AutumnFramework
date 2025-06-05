package com.chanj.autumn.beans.factory.support;

import com.chanj.autumn.beans.factory.DisposableBean;
import com.chanj.autumn.beans.factory.config.SingletonBeanRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{

    private Map<String, DisposableBean> disposableBeans = new HashMap<>();
    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void registerDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeans.put(beanName, disposableBean);
    }

    @Override
    public void destroySingletons() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Set<String> keySet = disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for(int i=disposableBeanNames.length-1; i>=0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            disposableBean.destroy();
        }
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
