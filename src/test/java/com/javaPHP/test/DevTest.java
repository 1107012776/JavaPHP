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
        //??????
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
        reqData.put("filename", "2020??????");
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
/*        PHP.print_r(PHP.preg_match_all("(\\D*)(\\d+)(.*)", "This order was placed for QT3000! OK?", matches));
        PHP.print_r(matches);*/

        PHP.print_r(PHP.preg_match_all("^0?(1[3-9]\\d{5})(\\d{4})", "18012323232", matches));
        PHP.print_r(matches);
        matches = new Matches();
        PHP.print_r(PHP.preg_match_all("^0?(1[3-9]\\d{5})(\\d{4})", "013683943885", matches));
        PHP.print_r(matches);

        matches = new Matches();
        PHP.print_r(PHP.preg_match_all("^(0?\\d{3})\\d{6,8}$", "05923023123", matches));
        PHP.print_r(matches);

        matches = new Matches();
        PHP.print_r(PHP.preg_match("^[2-9]\\d{6,7}$", "3023123"));
        PHP.print_r(matches);

        PHP.print_r(preg_match("^0?400\\d{7,}$", "4001028324"));

        matches = new Matches();

        if(PHP.preg_match_all("^(0?\\d{3})\\d{6,8}$", "51187220331", matches) > 0){
            List<String> matchList = matches.getAll();
            String number_fix =  matchList.get(1);
            if (number_fix.length() == 3) {
                number_fix = "0"+number_fix;
            }
            PHP.print_r(number_fix);
        }

    }

    @org.junit.Test
    public void testEnv() {
        Env config = PHP.createEnv(projectPath + "\\env.properties");
        String value = config.get("log4j.appender.console.Encoding", null);
        PHP.print_r(value);
    }


}


