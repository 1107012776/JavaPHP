package com.javaPHP.base;

import com.javaPHP.PHP;

import java.util.Arrays;

public class Function {
    public static Array array = new Array();

    public void echo(Object obj) {
        System.out.println(obj);
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

    public String substr(String oldStr, int beginIndex, int getLength) {
        int length = oldStr.length();
        if (length <= 0) {
            return oldStr;
        }
        if (beginIndex < 0) {
            int absLength = Math.abs(beginIndex);
            beginIndex = length - 1 - absLength;
            if (getLength < 0) {
                getLength = absLength + getLength;
            }
        } else {
            if (getLength < 0) {
                getLength = length - 1 - beginIndex + getLength;
            }
        }
        return oldStr.substring(beginIndex, beginIndex + getLength);
    }

    public String substr(String oldStr, int beginIndex) {
        int length = oldStr.length();
        if (length <= 0) {
            return oldStr;
        }
        if (beginIndex < 0) {
            int getLength = Math.abs(beginIndex);
            beginIndex = length - 1 - getLength;
        }
        return oldStr.substring(beginIndex);
    }
}
