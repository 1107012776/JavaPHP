package com.javaPHP.test;

import static org.junit.Assert.*;

import static com.javaPHP.PHP.*;
import com.javaPHP.*;
import com.javaPHP.base.*;

public class DevTest {

    @org.junit.Test
    public void testEcho() {
        PHP.echo("hello world");
        PHP.print_r("hello world");
        PHP.var_dump("hello world");
        var_dump("hello world");
    }

    @org.junit.Test
    public void testInArray() {
        String[] arr = new String[]{"a", "b", "c"};
        PHP.print_r(PHP.in_array("a", arr));
    }

    @org.junit.Test
    public void testRelpace() {
        PHP.print_r(PHP.str_replace("a", "b", "abcabc"));
    }


    @org.junit.Test
    public void testArrayMerge() {
        String[] arr1 = new String[]{"a", "b", "c"};
        String[] arr2 = new String[]{"a", "b", "c", "d"};
        String[] arr3 = (String[]) PHP.array_merge(arr1, arr2);
        PHP.print_r(arr3);
    }

    @org.junit.Test
    public void testFileGetContents() {
        String content = PHP.file_get_contents("D:\\git-project\\JavaPHP\\src\\test\\java\\com\\javaPHP\\test\\DevTest.java");
        PHP.print_r(content);
    }
}