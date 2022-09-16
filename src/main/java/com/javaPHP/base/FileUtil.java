package com.javaPHP.base;

import java.io.*;

//https://www.runoob.com/java/java-files-io.html
public class FileUtil {

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public byte[] readFileByBytes(String fileName) {
        File f = new File(fileName);
        InputStream inputStream = null;
        try {
            // 一次读一个字节
            inputStream = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            int len = -1;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new byte[0];
    }


    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public String readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        String content = "";
        try {
//          System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    content += String.valueOf((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public boolean fileWriter(String path, String content) {
        try {


            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            }

            //使用true，即进行append file

            FileWriter fileWritter = new FileWriter(file.getName(), true);


            fileWritter.write(content);

            fileWritter.close();

            return true;
        } catch (IOException e) {

            e.printStackTrace();
            return false;

        }
    }

    public boolean writeBytes(String path, byte[] content) {
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(path));
            outputStream.write(content);
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
