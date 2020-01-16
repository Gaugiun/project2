package com.stylefeng.guns.rest.order.util;

import java.io.*;

/**
 * @ClassName Json2String
 * @Description 用于将Json文件转换为String的工具类
 * @Author 王唯聪
 * @Data 2020/1/12 22:56
 * @Version 1.0
 **/
public class Json2String {
    public static String json2String(String path) throws IOException {
        StringBuilder result = new StringBuilder();
        InputStream in = new FileInputStream(path);
        //读取文件上的数据。  
        // 将字节流向字符流的转换。  
        InputStreamReader isr = new InputStreamReader(in,"UTF-8");//读取  
        //创建字符流缓冲区  
        BufferedReader bufr = new BufferedReader(isr);//缓冲  

        String line = null;
        while ((line = bufr.readLine()) != null) {
            result.append(System.lineSeparator()+line);
        }
        isr.close();
        return result.toString();
    }
}
