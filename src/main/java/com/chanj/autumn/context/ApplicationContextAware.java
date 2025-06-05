package com.chanj.autumn.context;

import com.chanj.autumn.beans.BeansException;
import com.chanj.autumn.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {
    public  void setApplicationContext(ApplicationContext applicationContext) throws BeansException ;
}
