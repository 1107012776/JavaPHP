package com.javaPHP.base;

import java.io.*;

//https://www.runoob.com/java/java-files-io.html
public class FileUtil {

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
/*    public void readFileByBytes(String fileName) {
        File f = new File(fileName);
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(f);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.println(tempbyte);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }*/

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


}
