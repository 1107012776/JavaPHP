package com.javaPHP.base;

import com.javaPHP.PHP;
import com.javaPHP.entity.*;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
    public static Array array = new Array();

    public void echo(Object obj) {
        System.out.println(obj);
    }

    public void var_dump(Object obj) {
        System.out.println(obj);
    }

    public void print_r(Object obj) {
        System.out.println(obj);
    }

    public String str_replace(String oldStr, String newStr, String targetValue) {
        return targetValue.replace(oldStr, newStr);
    }

    public String substr(String oldStr, int beginIndex, int getLength) {
        int length = oldStr.length();
        if (length <= 0) {
            return oldStr;
        }
        if (beginIndex < 0) {
            int absLength = Math.abs(beginIndex);
            beginIndex = length - absLength;
            if (getLength < 0) {
                getLength = absLength + getLength;
            }
        } else {
            if (getLength < 0) {
                getLength = length - beginIndex + getLength;
            }
        }
        return oldStr.substring(beginIndex, beginIndex + getLength);
    }

    public String substr(String oldStr, int beginIndex) {
        int length = oldStr.length();
        if (length <= 0) {
            return oldStr;
        }
        if (beginIndex < 0) {
            int getLength = Math.abs(beginIndex);
            beginIndex = length - getLength;
        }
        return oldStr.substring(beginIndex);
    }

    public boolean preg_match(String pattern, String content) {
        boolean isMatch = Pattern.matches(pattern, content);
        return isMatch;
    }

    public Integer preg_match_all(String pattern, String content, Matches matches) {
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(content);
        if (m.find()) {
            Integer groupCount = m.groupCount();
            for (int i = 0; i <= groupCount; i++) {
                matches.add(m.group(i));
            }
            return groupCount;
        } else {
            return 0;
        }
    }
}
