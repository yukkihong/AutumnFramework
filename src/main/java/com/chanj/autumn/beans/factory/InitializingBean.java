package com.chanj.autumn.beans.factory;

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
