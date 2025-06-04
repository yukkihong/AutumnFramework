package com.chanj.autumn.context.support;

import com.chanj.autumn.beans.factory.support.DefaultListableBeanFactory;
import com.chanj.autumn.beans.factory.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if(null != configLocations){
            reader.loadBeanDefintions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
