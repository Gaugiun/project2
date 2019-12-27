package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CskaoyanMallStorage;
import com.baidu.mall.service.SystemStorageServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("admin")
@RestController
public class SystemController {
    /**
     * 对象存储  admin/storage/list
     * page: 1
     * limit: 20
     * key: key    对象KEY
     * name: 010   对象名称
     * sort: add_time
     * order: desc
     */
    @Autowired
    SystemStorageServiceImpl systemStorageServiceImpl;

    @RequestMapping("storage/list")
    public BaseRespVo UserList(Integer page,Integer limit,Integer key,String name){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallStorage> storages = systemStorageServiceImpl.selectStorage(key,name);
        PageInfo<CskaoyanMallStorage> storagePageInfo = new PageInfo<>(storages);

        Map dataMap = new HashMap();
        dataMap.put("total",storagePageInfo.getTotal());
        dataMap.put("items",storages);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("storage/create")
    public BaseRespVo StorageCreate(HttpServletRequest request, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();  // name  happy.jpg
        String s = UUID.randomUUID().toString();  // url  7d6f9888-05b3-4e1c-ad5f-ad469d62d4a1
        String upload = request.getServletContext().getRealPath("upload"); // C:\Users\Lee\AppData\Local\Temp\tomcat-docbase.6297513146987024428.8081\upload


        System.out.println(originalFilename);
        System.out.println(s);
        System.out.println(upload);
        // path: d://spring   child: template.txt
        //File newfile = new File(upload, originalFilename);
        //file.transferTo(newfile);



/*        "id": 90,
        "key": "0uh2aax9m5drrqyr3cbl.jpg",
        "name": "happy.jpg",
        "type": "image/jpeg",
        "size": 4316,
        "url": "http://192.168.2.100:8081/wx/storage/fetch/0uh2aax9m5drrqyr3cbl.jpg",
        "addTime": "2019-12-27 02:36:03",
        "updateTime": "2019-12-27 02:36:03"*/


/*        String key = UUID.randomUUID().toString();
        Integer size = Math.toIntExact(file.getSize());
        //byte[] bytes = file.getBytes();
        String contentType = file.getContentType();
        String originalFilename = file.getOriginalFilename();
        Resource resource = file.getResource();
        String filename = resource.getFilename();
        System.out.println(filename);*/
        //URL url = file.getResource().getURL();

/*        System.out.println(size);  // 4316 -- size
        System.out.println(bytes); // ??
        System.out.println(contentType);   // image/.jpeg  -- type
        System.out.println(originalFilename);  // happy.jpg  -- name*/
/*        System.out.println(key);
        //System.out.println(url);

        //int i = systemStorageServiceImpl.insertStorage(key,size,originalFilename,contentType);

        String fieldName = file.getName();
        String fileName = item.getName();
        //System.out.println("upload:" + fieldName);
        //System.out.println("upload:" + fileName);
        //重名问题
        String prefix = UUID.randomUUID().toString();
        fileName = prefix + "-" + fileName;*/

        BaseRespVo resp = new BaseRespVo();
        //resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }


}
