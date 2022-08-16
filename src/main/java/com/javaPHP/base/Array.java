package com.javaPHP.base;

import java.util.Arrays;

public class Array {
    public boolean in_array(String targetValue, String[] arr) {
        return Arrays.asList(arr).contains(targetValue);
    }
}
