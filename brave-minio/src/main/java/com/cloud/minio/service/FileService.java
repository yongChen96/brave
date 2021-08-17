package com.cloud.minio.service;

import io.minio.*;
import java.io.InputStream;

/**
 * @author admin
 * @version 1.0
 * @description: 附件服务
 * @date 2021/6/28 11:52
 */
public interface FileService {

    /**
     * @Author: yongchen
     * @Description: 根据桶名称和对象信息获取存储信息
     * @Date: 14:27 2020/11/12
     * @Param: [bucketName, objectName]
     * @return: java.io.InputStream
     **/
    InputStream getObject(String bucketName, String objectName);

    /**
     * @Author: yongchen
     * @Description: 获取预览url
     * @Date: 14:40 2020/11/12
     * @Param: [bucketName, objectName, duration]
     * @return: java.lang.String
     **/
    String getObjectUrl(String bucketName, String objectName, int duration);

    /**
     * @Author: yongchen
     * @Description: 通过InputStream上传对象
     * @Date: 14:31 2020/11/12
     * @Param: [bucketName, objectName, iso, size]
     * @return: io.minio.ObjectWriteResponse
     **/
    ObjectWriteResponse putObject(String bucketName, String objectName, InputStream iso, Long size, String contentType);

    /**
     * @Author: yongchen
     * @Description: 上传文件
     * @Date: 14:44 2020/11/12
     * @Param: [bucketName, objectName, fileName]
     * @return: io.minio.ObjectWriteResponse
     **/
    ObjectWriteResponse uploadObject(String bucketName, String objectName, String fileName);

    /**
     * @Author: yongchen
     * @Description: 文件下载
     * @Date: 14:45 2020/11/12
     * @Param: [bucketName, objectName, fileName]
     * @return: void
     **/
    void downloadObject(String bucketName, String objectName, String fileName);

    /**
     * @Author: yongchen
     * @Description: 删除文件或文件夹
     * @Date: 14:49 2020/11/12
     * @Param: [bucketName, objectName]
     * @return: void
     **/
    void removeObject(String bucketName, String objectName);

    /**
     * @Author: yongchen
     * @Description: 判断对象是否存在
     * @Date: 14:51 2020/11/12
     * @Param: [bucketName, objectName]
     * @return: io.minio.StatObjectResponse
     **/
    StatObjectResponse statObject(String bucketName, String objectName);

    /**
     * @Author: yongchen
     * @Description: 判断文件夹是否存在
     * @Date: 14:06 2021/3/16
     * @Param: [bucketName, objectName]
     * @return: java.lang.Boolean
     **/
    Boolean doesFolderExist(String bucketName, String objectName);

    /**
     * @Author: yongchen
     * @Description: 创建文件夹
     * @Date: 17:58 2021/3/16
     * @Param: [bucketName, folderName]
     * @return: io.minio.ObjectWriteResponse
     **/
    ObjectWriteResponse createFolder(String bucketName, String folderName);
}
