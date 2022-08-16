package com.javaPHP.test;

import static org.junit.Assert.*;

import com.javaPHP.*;

public class ArrayUtilTest {

    @org.junit.Test
    public void testEcho() {
        PHP.function.echo("hello world");
    }

    @org.junit.Test
    public void testInArray() {
        String[] arr = new String[]{"a", "b", "c"};
        System.out.println(PHP.array.in_array("a", arr));
    }
}