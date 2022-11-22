package com.javaPHP;

import com.javaPHP.base.Array;
import com.javaPHP.base.Function;
import com.javaPHP.base.FileUtil;
import com.javaPHP.base.Http;

import java.lang.Integer;
import java.util.Arrays;

public class PHP {

    protected static Function function = new Function();

    protected static Array array = new Array();

    protected static FileUtil fileUtil = new FileUtil();

    public static void echo(Object obj) {
        function.echo(obj);
    }

    public static void var_dump(Object obj) {
        function.var_dump(obj);
    }

    public static void print_r(Object obj) {
        function.print_r(obj);
    }

    public static String str_replace(String oldStr, String newStr, String targetValue) {
        return function.str_replace(oldStr, newStr, targetValue);
    }

    public static boolean in_array(String targetValue, String[] arr) {
        return array.in_array(targetValue, arr);
    }

    public static Object[] array_merge(Object[] arr, Object[] arr1) {
        return array.array_merge(arr, arr1);
    }

    public static Object[] array_filter(Object[] arr) {
        return array.array_filter(arr);
    }

    public static String[] array_filter(String[] arr) {
        Object[] arr1 = array.array_filter(arr);
        String[] arr2 = Arrays.asList(arr1).toArray(new String[arr1.length]);
        return arr2;
    }

    public static Object[] array_unique(Object[] arr) {
        return array.array_unique(arr);
    }

    public static String[] array_unique(String[] arr) {
        Object[] arr1 = array.array_unique(arr);
        String[] arr2 = Arrays.asList(arr1).toArray(new String[arr1.length]);
        return arr2;
    }

    public static String file_get_contents(String path) {
        return fileUtil.readFileByChars(path);
    }

    public static byte[] readBytes(String path) {
        return fileUtil.readFileByBytes(path);
    }

    public static boolean file_put_contents(String path, String content) {
        return fileUtil.fileWriter(path, content);
    }

    public static boolean file_put_contents(String path, byte[] content) {
        return fileUtil.writeBytes(path, content);
    }

    public static String substr(String oldStr, int beginIndex, int getLength) {
        return function.substr(oldStr, beginIndex, getLength);
    }

    public static String substr(String oldStr, int beginIndex) {
        return function.substr(oldStr, beginIndex);
    }

    public static Integer strlen(String str) {
        return str.length();
    }

    public static Http createHttpClient() {
        return new Http();
    }
}
