package com.javaPHP.base;

import com.javaPHP.PHP;

import java.util.Arrays;

public class Function {
    public static Array array = new Array();

    public void echo(String str) {
        System.out.println(str);
    }

    public void var_dump(Object obj) {
        System.out.println(obj);
    }

    public void print_r(Object obj) {
        System.out.println(obj);
    }

    public String str_replace(String oldStr, String newStr, String targetValue) {
        return targetValue.replace(oldStr, newStr);
    }
}
