package com.chanj.autumn.beans.factory.config;

import com.chanj.autumn.beans.PropertyValues;

/**
 * Description:
 * Program Name:
 * Date: 2025-6-2
 * @author Chanj
 * @version 1.0
 */
public class BeanDefinition {

    private Class beanClass;

    private PropertyValues propertyValues;

    /**
     *
     * @param beanClass
     */
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

}
