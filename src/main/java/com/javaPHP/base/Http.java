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
                    return map.toString();
                }
            } else {
                for (String map : oldResponseHeaders.get(name)) {
                    return map.toString();
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


    public String upload(String serverUrl, Map<String, String> fileMap, Map<String, Object> reqData) {
        // 获取到要上传的文件的输入流信息，通过ByteArrayOutputStream流转成byte[]
        try {
            String result = "";
            // 调用自定义的post数据方法，提交表单数据及上传文件
            result = sendFileData(serverUrl, reqData, fileMap, "utf-8");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private String sendFileData(String serverUrl, Map<String, Object> reqData, Map<String, String> fileMap, String charset) throws IOException {


        // 设置三个常用字符串常量：换行、前缀、分界线（NEWLINE、PREFIX、BOUNDARY）；
        final String NEWLINE = "\r\n";
        final String PREFIX = "--";
        final String BOUNDARY = "#";

        HttpURLConnection httpConn = null;
        BufferedInputStream bis = null;
        DataOutputStream dos = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            File file = new File(fileMap.get("path"));
            BufferedInputStream bis1 = new BufferedInputStream(new FileInputStream(file));
            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            byte[] body_data = null;
            int c = 0;
            byte[] buffer = new byte[8 * 1024];
            while ((c = bis1.read(buffer)) != -1) {
                baos1.write(buffer, 0, c);
                baos1.flush();
            }
            body_data = baos1.toByteArray();
            baos1.close();
            // 实例化URL对象。调用URL有参构造方法，参数是一个url地址；
            URL urlObj = new URL(serverUrl);
            // 调用URL对象的openConnection()方法，创建HttpURLConnection对象；
            httpConn = (HttpURLConnection) urlObj.openConnection();
            // 调用HttpURLConnection对象setDoOutput(true)、setDoInput(true)、setRequestMethod("POST")；
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            // 设置Http请求头信息；（Accept、Connection、Accept-Encoding、Cache-Control、Content-Type、User-Agent）
            httpConn.setUseCaches(false);
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            httpConn.setRequestProperty("Cache-Control", "no-cache");
            // 这个比较重要，按照上面分析的拼装出Content-Type头的内容
            httpConn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            // 这个参数可以参考浏览器中抓出来的内容写，用chrome或者Fiddler抓吧看看就行
            httpConn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30)");
            // 调用HttpURLConnection对象的connect()方法，建立与服务器的真实连接；
            httpConn.connect();

            // 调用HttpURLConnection对象的getOutputStream()方法构建输出流对象；
            dos = new DataOutputStream(httpConn.getOutputStream());
            // 获取表单中上传控件之外的控件数据，写入到输出流对象（根据上面分析的抓包的内容格式拼凑字符串）；
            if (reqData != null && !reqData.isEmpty()) { // 这时请求中的普通参数，键值对类型的，相当于上面分析的请求中的username，可能有多个
                for (Map.Entry<String, Object> entry : reqData.entrySet()) {
                    String key = entry.getKey(); // 键，相当于上面分析的请求中的username
                    Object value = reqData.get(key); // 值，相当于上面分析的请求中的sdafdsa
                    dos.writeBytes(PREFIX + BOUNDARY + NEWLINE); // 像请求体中写分割线，就是前缀+分界线+换行
                    dos.writeBytes("Content-Disposition: form-data; "
                            + "name=\"" + key + "\"" + NEWLINE); // 拼接参数名，格式就是Content-Disposition: form-data; name="key" 其中key就是当前循环的键值对的键，别忘了最后的换行
                    dos.writeBytes(NEWLINE); // 空行，一定不能少，键和值之间有一个固定的空行
                    dos.writeBytes(URLEncoder.encode(value.toString(), charset)); // 将值写入
                    // 或者写成：dos.write(value.toString().getBytes(charset));
                    dos.writeBytes(NEWLINE); // 换行
                }
            }

            // 获取表单中上传附件的数据，写入到输出流对象（根据上面分析的抓包的内容格式拼凑字符串）；
            if (body_data != null && body_data.length > 0) {
                dos.writeBytes(PREFIX + BOUNDARY + NEWLINE);// 像请求体中写分割线，就是前缀+分界线+换行
                // 格式是:Content-Disposition: form-data; name="请求参数名"; filename="文件名"
                // 我这里吧请求的参数名写成了uploadFile，是死的，实际应用要根据自己的情况修改
                // 不要忘了换行
                dos.writeBytes("Content-Disposition: form-data; " + "name=\""
                        + fileMap.get("key") + "\"" + "; filename=\"" + file.getName()
                        + "\"" + NEWLINE);
                // 换行，重要！！不要忘了
                dos.writeBytes(NEWLINE);
                dos.write(body_data); // 上传文件的内容
                dos.writeBytes(NEWLINE); // 最后换行
            }
            dos.writeBytes(PREFIX + BOUNDARY + PREFIX + NEWLINE);
            // 最后的分割线，与前面的有点不一样是前缀+分界线+前缀+换行，最后多了一个前缀
            dos.flush();

            // 调用HttpURLConnection对象的getInputStream()方法构建输入流对象；
            buffer = new byte[8 * 1024];
            c = 0;
            // 调用HttpURLConnection对象的getResponseCode()获取客户端与服务器端的连接状态码。如果是200，则执行以下操作，否则返回null；
            if (httpConn.getResponseCode() == 200) {
                bis = new BufferedInputStream(httpConn.getInputStream());
                while ((c = bis.read(buffer)) != -1) {
                    baos.write(buffer, 0, c);
                    baos.flush();
                }
            }
            // 将输入流转成字节数组，返回给客户端。
            return new String(baos.toByteArray(), charset);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null)
                    dos.close();
                if (bis != null)
                    bis.close();
                if (baos != null)
                    baos.close();
                httpConn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean setHeaders(Map<String, String> mapHeader) {
        return headers.add(mapHeader);
    }

}
