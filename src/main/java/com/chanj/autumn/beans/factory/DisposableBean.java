package com.chanj.autumn.beans.factory;

import java.lang.reflect.InvocationTargetException;

public interface DisposableBean {
    void destroy() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
