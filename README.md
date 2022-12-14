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
import java.util.List;
import java.util.ArrayList;

import com.javaPHP.entity.*;

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
    public void testArrayFilter() {
        String[] arr1 = new String[]{"a", "b", "c", null, ""};
        String[] arr3 = PHP.array_filter(arr1);
        PHP.print_r(arr3);
    }

    @org.junit.Test
    public void testArrayUnique() {
        String[] arr1 = new String[]{"a", "b", "c", null, "", "c"};
        String[] arr3 = PHP.array_unique(arr1);
        PHP.print_r(arr3);
    }

    @org.junit.Test
    public void testListToArr() {
        List<String> person = new ArrayList<String>();
        person.add("jackie");
        person.add("peter");
        person.add("annie");
        person.add("martin");
        person.add("marry");
        String[] arr3 = PHP.listToStrArr(person);
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


    @org.junit.Test
    public void testHttpClientUpload() {
        Map<String, Object> reqData = new HashMap<String, Object>();
        reqData.put("name", "uploadFile");
        reqData.put("filename", "2020账单");
        Http client = PHP.createHttpClient();
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("key", "uploadFile");
        fileMap.put("path", projectPath + "\\pom.xml");
        String message = client.upload(PHP.file_get_contents(projectPath + "\\dev\\cansu.txt"), fileMap, reqData);
        PHP.print_r(message);
    }


    @org.junit.Test
    public void testPregMatch() {
        PHP.print_r(PHP.preg_match(".*b.*", "abc"));
        Matches matches = new Matches();
        PHP.print_r(PHP.preg_match_all("(\\D*)(\\d+)(.*)", "This order was placed for QT3000! OK?", matches));
        PHP.print_r(matches);
    }

    @org.junit.Test
    public void testEnv() {
        Env config = PHP.createEnv(projectPath + "\\env.properties");
        String value = config.get("log4j.appender.console.Encoding", null);
        PHP.print_r(value);
    }

}



```

