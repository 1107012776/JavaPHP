package com.javaPHP.base;

public class Function {
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