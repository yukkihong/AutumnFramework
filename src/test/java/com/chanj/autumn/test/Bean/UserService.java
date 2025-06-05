package com.chanj.autumn.test.Bean;

import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.factory.*;
import com.chanj.autumn.context.ApplicationContext;
import com.chanj.autumn.context.ApplicationContextAware;

import java.lang.reflect.InvocationTargetException;

public class UserService implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public String queryUserInfo() {
        return userDao.queryUserName(uId)+", 公司："+company+", 地点"+location;
    }

    // ...get/set

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getuId() {
        return uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

//    @Override
//    public void destroy() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        System.out.println("执行：UserService.destroy");
//    }
//
//
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("执行：UserService.afterPropertiesSet");
//    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader:" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("Bean Name is:" + beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
