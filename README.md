# JavaPHP
用Java封装实现通用的PHP方法和函数或者类，便于PHP程序员开发Java项目

# MAVEN引入(示例如下)
```
    <dependencies>
        <dependency>
            <groupId>com</groupId>
            <artifactId>javaPHP</artifactId>
            <version>1.4.1</version>
        </dependency>
    </dependencies>
    <repositories>
        <repository>
            <id>javaPHP</id>
            <url>https://raw.githubusercontent.com/1107012776/maven-repo/master/repository/</url>
        </repository>
    </repositories>
```

# 基础

```java
package com.javaPHP.test;

import static org.junit.Assert.*;

import static com.javaPHP.PHP.*;

import com.javaPHP.*;
import com.javaPHP.base.*;

import java.util.HashMap;
import java.util.Map;

public class DevTest {

    public static String projectPath = "E:\\git-project\\JavaPHP";

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

        Map<String, String> header = new HashMap<String, String>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("username", PHP.file_get_contents(projectPath + "\\dev\\username.txt"));
        param.put("password", PHP.file_get_contents(projectPath + "\\dev\\password.txt"));
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        String message = "";
/*      message = client.doPost(PHP.file_get_contents(projectPath + "\\dev\\url.txt"), param);
        PHP.print_r(message);
        client = PHP.createHttpClient();
        message = client.doGet(PHP.file_get_contents(projectPath + "\\dev\\first_url.txt"));
        PHP.print_r(message);*/
        Http client = PHP.createHttpClient();
        client.setHeaders(header);
        message = client.doPost(PHP.file_get_contents(projectPath + "\\dev\\cansu.txt"), param);
        PHP.print_r(message);
        client = PHP.createHttpClient();
        header = new HashMap<String, String>();
        header.put("Content-Type", "application/json; charset=UTF-8");
        client.setHeaders(header);
        message = client.doPut(PHP.file_get_contents(projectPath + "\\dev\\cansu.txt"), param);
        PHP.print_r(message);
        PHP.print_r(client.getResponseHeaders());
        PHP.print_r(client.getResponseHeaders("Server"));
    }
}
```

