package com.javaPHP;

import java.util.Arrays;

public class Array {
    public static boolean in_array(String targetValue, String[] arr){
        return Arrays.asList(arr).contains(targetValue);
    }
}
