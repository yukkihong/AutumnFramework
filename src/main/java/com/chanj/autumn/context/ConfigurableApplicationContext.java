package com.chanj.autumn.context;

import com.chanj.autumn.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
