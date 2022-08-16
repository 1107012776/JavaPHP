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
        PHP.function.print_r(PHP.array.in_array("a", arr));
    }

    @org.junit.Test
    public void testRelpace() {
        PHP.function.print_r(PHP.function.str_replace("a", "b", "abcabc"));
    }
}