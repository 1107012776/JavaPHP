package com.javaPHP.test;

import static org.junit.Assert.*;

import static com.javaPHP.PHP.*;

import com.javaPHP.*;
import com.javaPHP.base.*;

import java.util.HashMap;
import java.util.Map;

public class DevTest {

    public static String projectPath = "D:\\git-project\\JavaPHP";

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
        String content = PHP.file_get_contents(this.getClass().getResource("/").getPath() + "/com/javaPHP/test/DevTest.class");
        PHP.print_r(content);
        content = PHP.file_get_contents(projectPath + "\\pom.xml");
        boolean create = PHP.file_put_contents(projectPath + "\\pom1.xml", content);
        PHP.print_r(create);
    }

    @org.junit.Test
    public void testReadBytes() {
        byte[] content = PHP.readBytes(projectPath + "\\pom.xml");
        //Convert back to String
        String s = new String(content);
        PHP.print_r(s);
        //图片
        content = PHP.readBytes(projectPath + "\\test.png");
        boolean create = PHP.file_put_contents(projectPath + "\\test1.png", content);
        PHP.print_r(create);
    }

    @org.junit.Test
    public void testSubstr() {
        String Str = new String("This is text");
        PHP.print_r(PHP.substr(Str, 5));  //is text
        PHP.print_r(PHP.substr(Str, 5, 5)); //is te
        PHP.print_r(PHP.substr(Str, -1)); //t
        PHP.print_r(PHP.substr(Str, -3, 1)); //e
        PHP.print_r(PHP.substr(Str, -3, 2));  //ex
        PHP.print_r(PHP.substr(Str, -4, 2));  //te
        PHP.print_r(PHP.substr(Str, -4, -1)); //tex
    }

    @org.junit.Test
    public void testHttpClient() {
        Http client = PHP.createHttpClient();
        Map<String,String> header = new HashMap<String,String>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("username","zhan");
        param.put("password","mima");
        header.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        client.setHeaders(header);
        String message = client.doPost("https://baidu.com/login", param);
        System.out.println(message);
    }
}