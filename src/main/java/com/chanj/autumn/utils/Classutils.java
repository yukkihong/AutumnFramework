package com.chanj.autumn.utils;

import com.chanj.autumn.core.io.Resource;

public class Classutils {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try{
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch(Throwable ex){
            //
        }
        if(cl == null){
            cl = Classutils.class.getClassLoader();
        }
        return cl;
    }
}
