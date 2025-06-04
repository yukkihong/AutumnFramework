package com.chanj.autumn.test;

import cn.hutool.core.io.IoUtil;
import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.PropertyValue;
import com.chanj.autumn.beans.PropertyValues;
import com.chanj.autumn.beans.factory.config.BeanDefinition;
import com.chanj.autumn.beans.factory.config.BeanReference;
import com.chanj.autumn.beans.factory.support.DefaultListableBeanFactory;
import com.chanj.autumn.beans.factory.xml.XmlBeanDefinitionReader;
import com.chanj.autumn.context.support.ClassPathXmlApplicationContext;
import com.chanj.autumn.core.io.DefaultResourceLoader;
import com.chanj.autumn.core.io.Resource;
import com.chanj.autumn.test.Bean.UserDao;
import com.chanj.autumn.test.Bean.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class ApiTest {
    private DefaultResourceLoader resourceLoader;

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:autumn.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }


}
