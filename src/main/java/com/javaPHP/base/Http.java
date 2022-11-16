package com.javaPHP.base;


import com.javaPHP.PHP;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.Map;
import java.util.HashMap;
import java.net.URLEncoder;

public class Http {

    private List<Map<String, String>> headers;

    private Integer responseCode;

    private Map<String, List<String>> responseHeaders;

    public Http() {
        headers = new ArrayList<Map<String, String>>();
    }

    /**
     * Http get请求
     *
     * @param httpUrl 连接
     * @return 响应数据
     */
    public String doGet(String httpUrl) {
        return doCommon(httpUrl, null, "GET");
    }

    /**
     * Http put请求
     *
     * @param httpUrl 连接
     * @return 响应数据
     */
    public String doPut(String httpUrl, Object param) {
        return doCommon(httpUrl, param, "PUT");
    }


    /**
     * Http delete请求
     *
     * @param httpUrl 连接
     * @return 响应数据
     */
    public String doDelete(String httpUrl, Object param) {
        return doCommon(httpUrl, param, "DELETE");
    }

    /**
     * Http post请求
     *
     * @param httpUrl 连接
     * @param param   参数
     * @return
     */
    public String doPost(String httpUrl, Object param) {
        return doCommon(httpUrl, param, "POST");
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }


    public String getResponseHeaders(String name) {
        for (String key : responseHeaders.keySet()) {
            if (name == null) {
                for (String map : responseHeaders.get(key)) {
                    return map.toString();
                }
            } else {
                for (String map : responseHeaders.get(name)) {
                    return map.toString();
                }
            }
        }
        return "";
    }

    public String getOldResponseHeaders(Map<String, List<String>> oldResponseHeaders, String name) {
        for (String key : oldResponseHeaders.keySet()) {
            if (name == null) {
                for (String map : oldResponseHeaders.get(key)) {
                    return map;
                }
            } else {
                for (String map : oldResponseHeaders.get(name)) {
                    return map;
                }
            }
        }
        return "";
    }

    public String doCommon(String httpUrl, Object param, String method) {
        StringBuffer result = new StringBuffer();
        //连接
        HttpURLConnection connection = null;
        OutputStream os = null;
        InputStream is = null;
        BufferedReader br = null;
        try {
            //创建连接对象
            URL url = new URL(httpUrl);
            //创建连接
            connection = (HttpURLConnection) url.openConnection();
            //设置请求方法
            connection.setRequestMethod(method);
            //设置连接超时时间
            connection.setConnectTimeout(15000);
            //设置读取超时时间
            connection.setReadTimeout(15000);
            //DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //设置是否可读取
            connection.setDoOutput(true);
            connection.setDoInput(true);
            //设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
//          connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            for (Map<String, String> map : headers) {
                for (String key : map.keySet()) {
                    connection.setRequestProperty(key, map.get(key));
                }
            }
            String paramBuild = "";
            if (connection.getRequestProperty("Content-Type") != null
                    && connection.getRequestProperty("Content-Type").indexOf("application/json") >= 0) {
                if (param != null && param instanceof Map) {
                    Map<String, Object> paramMap = (Map<String, Object>) param;
                    for (String key : paramMap.keySet()) {
                        String val = "";
                        Object value = paramMap.get(key);
                        if (value != null && value instanceof String) {
                            val = "\"" + value.toString() + "\"";
                        } else if (value == null) {
                            val = null;
                        } else {
                            val = value.toString();
                        }
                        if (paramBuild.equals("")) {
                            paramBuild += "{\"" + key + "\":" + val + ",";
                        } else {
                            paramBuild += "\"" + key + "\":" + val + ",";
                        }
                    }
                    if (!paramBuild.equals("")) {
                        paramBuild = PHP.substr(paramBuild, 0, PHP.strlen(paramBuild) - 1);
                        paramBuild += "}";
                    }
                }
            } else {
                if (param != null && param instanceof Map) {
                    Map<String, Object> paramMap = (Map<String, Object>) param;
                    for (String key : paramMap.keySet()) {
                        if (paramBuild.equals("")) {
                            paramBuild += key + "=" + URLEncoder.encode(paramMap.get(key).toString());
                        } else {
                            paramBuild += "&" + key + "=" + URLEncoder.encode(paramMap.get(key).toString());
                        }
                    }
                }
            }
            //拼装参数
            if (null != paramBuild && !paramBuild.equals("")) {
                //设置参数
                os = connection.getOutputStream();
                //拼装参数
                os.write(paramBuild.getBytes("UTF-8"));
            }
            //设置权限
            //设置请求头等
            //开启连接
            //connection.connect();
            //读取响应
            responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                is = connection.getInputStream();
                if (null != is) {
//                    br = new BufferedReader(new InputStreamReader(is, "GBK"));
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String temp = null;
                    while (null != (temp = br.readLine())) {
                        result.append(temp);
                        result.append("\r\n");
                    }
                }
            }
//            System.out.println(connection.getHeaderField("Server"));
            responseHeaders = connection.getHeaderFields();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭连接
            connection.disconnect();
        }
        return result.toString();
    }

    public boolean setHeaders(Map<String, String> mapHeader) {
        return headers.add(mapHeader);
    }

}
