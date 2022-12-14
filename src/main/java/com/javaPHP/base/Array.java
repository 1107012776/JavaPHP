package com.javaPHP.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.javaPHP.PHP;

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

    /**
     * 去重并去除空字符串或者null
     *
     * @param arr
     * @return
     */
    public Object[] array_filter(Object[] arr) {
        //创建一个集合
        List<Object> list = new ArrayList();
        //遍历数组往集合里存元素
        for (int i = 0; i < arr.length; i++) {
            //如果集合里面没有相同的元素才往里存
            if (!list.contains(arr[i])) {
                if (arr[i] != null) {
                    if (arr[i] instanceof String) {
                        String tmp = (String) arr[i];
                        if (!tmp.equals("")) {
                            list.add(arr[i]);
                        }
                    } else {
                        list.add(arr[i]);
                    }
                }
            }
        }
        //toArray()方法会返回一个包含集合所有元素的String类型数组
        Object[] newArr = (Object[]) list.toArray(new Object[0]);
        return newArr;
    }

    /**
     * 去重
     *
     * @param arr
     * @return
     */
    public Object[] array_unique(Object[] arr) {
        //创建一个集合
        List<Object> list = new ArrayList();
        //遍历数组往集合里存元素
        for (int i = 0; i < arr.length; i++) {
            //如果集合里面没有相同的元素才往里存
            if (!list.contains(arr[i])) {
                list.add(arr[i]);
            }
        }
        //toArray()方法会返回一个包含集合所有元素的String类型数组
        Object[] newArr = (Object[]) list.toArray(new Object[0]);
        return newArr;
    }

    /**
     * 转为String数组
     *
     * @param arr
     * @return
     */
    public String[] listToStrArr(Object arr) {
        if (arr instanceof List) {
            for (Object d : (List<Object>) arr) {
                if (d instanceof Integer) {
                    return getListIntegerToStrArr((List<Integer>) arr);
                }
                if (d instanceof Long) {
                    return getListLongToStrArr((List<Long>) arr);
                }
                if (d instanceof String) {
                    return getListStringToStrArr((List<String>) arr);
                }
            }
        }
        return new String[0];  //空数组
    }

    /**
     * 转为数组
     *
     * @param arr
     * @return
     */
    protected String[] getListLongToStrArr(List<Long> arr) {
        String arrStr = "";
        for (Long a : arr) {
            arrStr += "," + a.toString();
        }
        if (arrStr.equals("")) {
            return new String[0];  //空数组
        }
        arrStr = PHP.substr(arrStr, 1);
        return arrStr.split(",");
    }

    /**
     * 转为数组
     *
     * @param arr
     * @return
     */
    protected String[] getListIntegerToStrArr(List<Integer> arr) {
        String arrStr = "";
        for (Integer a : arr) {
            arrStr += "," + a.toString();
        }
        if (arrStr.equals("")) {
            return new String[0];  //空数组
        }
        arrStr = PHP.substr(arrStr, 1);
        return arrStr.split(",");
    }

    /**
     * 转为数组
     *
     * @param arr
     * @return
     */
    protected String[] getListStringToStrArr(List<String> arr) {
        String arrStr = "";
        for (String a : arr) {
            arrStr += "," + a;
        }
        if (arrStr.equals("")) {
            return new String[0];  //空数组
        }
        arrStr = PHP.substr(arrStr, 1);
        return arrStr.split(",");
    }
}
