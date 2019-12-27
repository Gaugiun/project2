/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/11/21
 * Time: 17:31
 */
package com.baidu.mall.service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class FileUploadUtils {


    public static Map parseRequest(HttpServletRequest request){
        // 创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置参数
        ServletContext servletContext = request.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        // 创建一个新的文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置上传的文件名乱码问题
        upload.setHeaderEncoding("utf-8");

        Map<String,String> map = new HashMap<>();
        try {
            // 处理器处理请求 --> 得到一个List
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();
            while (iterator.hasNext()){
                FileItem item = iterator.next();
                if (item.isFormField()) {
                    // 处理普通表单
                    processFormField(item, map);
                } else {
                    // 处理上传文件
                    processUploadedFile(request, item, map);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
}

    private static void processUploadedFile(HttpServletRequest request, FileItem item, Map<String, String> map) {
        String fieldName = item.getFieldName();
        String fileName = item.getName();
        //System.out.println("upload:" + fieldName);
        //System.out.println("upload:" + fileName);
        //重名问题
        String prefix = UUID.randomUUID().toString();
        fileName = prefix + "-" + fileName;

/*        int hashCode = fileName.hashCode();
        //转成16进制
        String hexString = Integer.toHexString(hashCode);
        char[] chars = hexString.toCharArray();
        String base = "";
        for (char aChar : chars) {
            base = base + "/" + aChar;
        }
        //  /upload/1/2/a/4/6/3/5/7/hakjdshakjd-1.jpg
        fileName = base + "/" + fileName;*/
        String upload = request.getServletContext().getRealPath("upload");
        File file = new File(upload + "/" + fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            item.write(file);
            //相对路径   可以存放相对应用根目录的一个路径   存放相对当前upload目录的路径
            //user.setImage(fileName);
            map.put(fieldName,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析普通form表单数据，需要考虑到如果是传入的是checkbox
     * 那么应当将相同的数据拼接成字符串的形式
     * @param item
     * @param map
     */
    private static void processFormField(FileItem item, Map<String, String> map) {
        String name = item.getFieldName();
        String value = null;
        try {
            value = item.getString("utf-8");
            //有bug的
            //map要进行一个处理，如果有相同的key，则不执行覆盖
            if(map.get(name) != null){
                value = map.get(name) + "," + value;
            }
            map.put(name,value);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

}

