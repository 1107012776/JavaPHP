package com.javaPHP.base;

import java.util.Arrays;

public class Array {
    public boolean in_array(String targetValue, String[] arr) {
        return Arrays.asList(arr).contains(targetValue);
    }

    public Object[] array_merge(Object[] arr, Object[] arr1) {
        int strLen1 = arr.length;// 保存第一个数组长度
        int strLen2 = arr1.length;// 保存第二个数组长度
        arr = Arrays.copyOf(arr, strLen1 + strLen2);// 扩容
        System.arraycopy(arr1, 0, arr, strLen1, strLen2);// 将第二个数组与第一个数组合并
        return arr;
    }
}
