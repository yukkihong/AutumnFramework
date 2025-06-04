package com.chanj.autumn.core.io;

import cn.hutool.core.lang.Assert;
import com.chanj.autumn.utils.Classutils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource {
    private final String path;
    private  ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader)null);
    }

    public ClassPathResource(String path, ClassLoader classLoader){
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : Classutils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(this.path + "cannot be opened because it does not exist");
        }
        return is;
    }
}
