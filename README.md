# JavaPHP
用Java封装实现通用的PHP方法和函数或者类，便于PHP程序员开发Java项目


# 基础

```java
package com.javaPHP.test;

import static org.junit.Assert.*;

import com.javaPHP.*;

public class ArrayUtilTest {

    @org.junit.Test
    public void testEcho() {
        PHP.function.echo("hello world");
        PHP.function.print_r("hello world");
        PHP.function.var_dump("hello world");
    }

    @org.junit.Test
    public void testInArray() {
        String[] arr = new String[]{"a", "b", "c"};
        PHP.function.print_r(PHP.function.in_array("a", arr));
    }

    @org.junit.Test
    public void testRelpace() {
        PHP.function.print_r(PHP.function.str_replace("a", "b", "abcabc"));
    }


    @org.junit.Test
    public void testArrayMerge() {
        String[] arr1 = new String[]{"a", "b", "c"};
        String[] arr2 = new String[]{"a", "b", "c", "d"};
        String[] arr3 = (String[])PHP.function.array_merge(arr1, arr2);
        PHP.function.print_r(arr3);
    }
}
```
