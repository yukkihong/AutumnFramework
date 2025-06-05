package com.chanj.autumn.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.PropertyValue;
import com.chanj.autumn.beans.factory.config.BeanDefinition;
import com.chanj.autumn.beans.factory.config.BeanReference;
import com.chanj.autumn.beans.factory.support.AbstractBeanDefinitionReader;
import com.chanj.autumn.beans.factory.support.BeanDefinitionRegistry;
import com.chanj.autumn.core.io.Resource;
import com.chanj.autumn.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try{
            try(InputStream inputStream = resource.getInputStream()){
                doLoadBeanDefinitions(inputStream);
            }
        }catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from" + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for(Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String lcoation) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(lcoation);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefintions(String... lcoations) throws BeansException {
        for(String lcoation : lcoations) {
            loadBeanDefinitions(lcoation);
        }
    }


    protected void doLoadBeanDefinitions(InputStream inputStream) throws BeansException, ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for(int i=0; i<childNodes.getLength(); i++) {
            if(!(childNodes.item(1) instanceof Element)) continue;

            if(!"bean".equals(childNodes.item(i).getNodeName())) continue;

            // 解析标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String className = bean.getAttribute("class");
            String name = bean.getAttribute("name");
            String InitMethod = bean.getAttribute("init-method");
            String destroyMethod = bean.getAttribute("destroy-method");
            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id)?id:name;
            if(StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            beanDefinition.setInitMethodName(InitMethod);
            beanDefinition.setDestroyMethodName(destroyMethod);

            for(int j=0; j<bean.getChildNodes().getLength(); j++) {
                if(!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if(!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;

                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                Object value = StrUtil.isNotEmpty(attrRef)?new BeanReference(attrRef):attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);

                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            if(getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }

            getRegistry().registerBeanDefinition(beanName, beanDefinition);



        }
    }
}
