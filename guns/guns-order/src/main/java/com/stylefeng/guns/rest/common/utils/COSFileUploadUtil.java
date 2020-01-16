package com.stylefeng.guns.rest.common.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;

import java.io.File;
import java.net.URL;
import java.util.Date;

public class COSFileUploadUtil {
    public static URL picCOS(File file) throws Exception {
        String secretId = "AKIDhRHxubnZ9UbnX8LYQ5YliaJn9IF9ZTxH";
        String secretKey = "FE3ex4xfhY9XoulFanjigaHl1BAuQmDT";
        String bucketName = "qwddfty-1256376956";
        COSCredentials credentials = new BasicCOSCredentials(secretId, secretKey);

        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));

        COSClient cosClient = new COSClient(credentials, clientConfig);
        String fileName = file.getName();

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file);
        cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
        URL url = cosClient.generatePresignedUrl(bucketName, fileName, expiration);
        return url;
    }
}
