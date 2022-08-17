package com.javaPHP;

import com.javaPHP.base.Array;
import com.javaPHP.base.Function;

public class PHP {

    protected static Function function = new Function();

    protected static Array array = new Array();

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
}
