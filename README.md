# JavaPHP
一个语法转换插件，封装实现通用的PHP方法和函数或者类，便于PHP程序员开发Java项目


# 基础

```
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
}
```