package com.javaPHP.base;

import java.util.Properties;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;

public class Env {
    protected Properties pro;

    public Env(String path) throws FileNotFoundException, IOException {
        pro = new Properties();//不需要加泛型，所有的键值都是object类型
        if (path.indexOf(".xml") > 0 || path.indexOf(".XML") > 0) {
            FileInputStream f2 = new FileInputStream(path);  //读取二进制
            pro.loadFromXML(f2);
        } else {
            FileReader fr = new FileReader(path);  //读取文本
            pro.load(fr);
        }
    }

    public Env set(String key, String value) {
        pro.put(key, value);
        return this;
    }

    public String get(String key, String defaultValue) {
        Object value = pro.get(key);
        if (value == null) {
            return defaultValue;
        }
        return (String) value;
    }
}
