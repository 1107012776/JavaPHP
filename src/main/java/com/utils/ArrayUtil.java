package com.utils;


import java.util.*;


public class ArrayUtil {
    /**
     * 去掉数组中重复的元素
     *
     * @param arr
     * @return
     */
    //方法一：需要传入一个String数组，然后返回去重后的数组
    public static String[] deduplication(String[] arr) {
        //创建一个集合
        List<String> list = new ArrayList();
        //遍历数组往集合里存元素
        for (int i = 0; i < arr.length; i++) {
            //如果集合里面没有相同的元素才往里存
            if (!list.contains(arr[i])) {
                list.add(arr[i]);
            }
        }
        //toArray()方法会返回一个包含集合所有元素的String类型数组
        String[] newArr = (String[]) list.toArray(new String[0]);
        return newArr;
    }


    /**
     * 去重并去除空字符串或者null
     *
     * @param arr
     * @return
     */
    public static String[] filterEmptyStr(String[] arr) {
        //创建一个集合
        List<String> list = new ArrayList();
        //遍历数组往集合里存元素
        for (int i = 0; i < arr.length; i++) {
            //如果集合里面没有相同的元素才往里存
            if (!list.contains(arr[i])
                    && !arr[i].equals("")
                    && !arr[i].equals(null)
            ) {
                list.add(arr[i]);
            }
        }
        //toArray()方法会返回一个包含集合所有元素的String类型数组
        String[] newArr = (String[]) list.toArray(new String[0]);
        return newArr;
    }

    /**
     * 转为数组
     *
     * @param arr
     * @return
     */
    public static String[] getListLongToArr(List<Long> arr) {
        String arrStr = "";
        for (Long a : arr) {
            arrStr += "," + a.toString();
        }
        if (arrStr.equals("")) {
            return new String[0];  //空数组
        }
        return arrStr.split(",");
    }

    /**
     * 转为数组
     *
     * @param arr
     * @return
     */
    public static String[] getListIntegerToArr(List<Integer> arr) {
        String arrStr = "";
        for (Integer a : arr) {
            arrStr += "," + a.toString();
        }
        if (arrStr.equals("")) {
            return new String[0];  //空数组
        }
        return arrStr.split(",");
    }


    /**
     * 合并数组
     *
     * @param String[] arr
     * @param String[] arr1
     * @return
     */
    public static String[] mergeArr(String[] arr, String[] arr1) {
        int strLen1 = arr.length;// 保存第一个数组长度
        int strLen2 = arr1.length;// 保存第二个数组长度
        arr = Arrays.copyOf(arr, strLen1 + strLen2);// 扩容
        System.arraycopy(arr1, 0, arr, strLen1, strLen2);// 将第二个数组与第一个数组合并
        return arr;
    }

    /**
     * 判断字符串是否在数组内
     *
     * @param arr
     * @param targetValue
     * @return
     */
    public static boolean contains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

}

