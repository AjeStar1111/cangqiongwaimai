package com.sky.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 阿里云OSS文件上传工具类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliOssUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 文件上传
     *
     * @param bytes      文件字节数组
     * @param objectName 对象名（含扩展名）
     * @return 文件访问路径
     */
    public String upload(byte[] bytes, String objectName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(bytes));
            ossClient.putObject(putObjectRequest);
        } finally {
            ossClient.shutdown();
        }
        // 文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);
        return stringBuilder.toString();
    }

    /**
     * 文件上传
     *
     * @param inputStream 输入流
     * @param objectName  对象名（含扩展名）
     * @return 文件访问路径
     */
    public String upload(InputStream inputStream, String objectName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, objectName, inputStream);
            ossClient.putObject(putObjectRequest);
        } finally {
            ossClient.shutdown();
        }
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);
        return stringBuilder.toString();
    }
}
