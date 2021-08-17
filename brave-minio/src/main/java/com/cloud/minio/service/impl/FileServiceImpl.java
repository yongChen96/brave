package com.cloud.minio.service.impl;

import com.cloud.minio.service.FileService;
import com.cloud.minio.utils.BucketUtils;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author yongchen
 * @description: 附件服务
 * @date 2021/8/13 17:43
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private MinioClient minioClient;
    @Resource
    private BucketUtils bucketUtils;

    @Value("${minio.bucketName}")
    private String defaultBucketName;

    /**
     * @Author: yongchen
     * @Description: 根据桶名称和对象信息获取存储信息
     * @Date: 14:27 2020/11/12
     * @Param: [bucketName, objectName]
     * @return: java.io.InputStream
     **/
    @Override
    public InputStream getObject(String bucketName, String objectName) {
        try {
            if (StringUtils.isBlank(bucketName)){
                bucketName = defaultBucketName;
            }
            if (bucketUtils.bucketExists(bucketName)) {
                return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
            }
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 获取文件失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 获取预览url
     * @Date: 14:40 2020/11/12
     * @Param: [bucketName, objectName, duration]
     * @return: java.lang.String
     **/
    @Override
    public String getObjectUrl(String bucketName, String objectName, int duration) {
        try {
            if (bucketUtils.bucketExists(bucketName)) {
                return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(duration, TimeUnit.DAYS)
                        .build());
            }
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 获取文件url失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 通过InputStream上传对象
     * @Date: 14:31 2020/11/12
     * @Param: [bucketName, objectName, iso, size]
     * @return: io.minio.ObjectWriteResponse
     **/
    @Override
    public ObjectWriteResponse putObject(String bucketName, String objectName, InputStream iso, Long size, String contentType) {
        try {
            if (StringUtils.isBlank(bucketName)){
                bucketName = defaultBucketName;
            }
            if (bucketUtils.bucketExists(bucketName)) {
                return minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(iso, size, -1)
                        .contentType(contentType)
                        .build());
            }
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 上传文件失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 上传文件
     * @Date: 14:44 2020/11/12
     * @Param: [bucketName, objectName, fileName]
     * @return: io.minio.ObjectWriteResponse
     **/
    @Override
    public ObjectWriteResponse uploadObject(String bucketName, String objectName, String fileName) {
        try {
            if (bucketUtils.bucketExists(bucketName)) {
                return minioClient.uploadObject(UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .filename(fileName)
                        .build());
            }
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 上传文件失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 文件下载
     * @Date: 14:45 2020/11/12
     * @Param: [bucketName, objectName, fileName]
     * @return: void
     **/
    @Override
    public void downloadObject(String bucketName, String objectName, String fileName) {
        try {
            if (bucketUtils.bucketExists(bucketName)) {
                minioClient.downloadObject(DownloadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .filename(fileName)
                        .build());
            }
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 下载文件失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author: yongchen
     * @Description: 删除文件或文件夹
     * @Date: 14:49 2020/11/12
     * @Param: [bucketName, objectName]
     * @return: void
     **/
    @Override
    public void removeObject(String bucketName, String objectName) {
        try {
            if (bucketUtils.bucketExists(bucketName)) {
                minioClient.removeObject(RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build());
            }
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 删除文件失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author: yongchen
     * @Description: 判断对象是否存在
     * @Date: 14:51 2020/11/12
     * @Param: [bucketName, objectName]
     * @return: io.minio.StatObjectResponse
     **/
    @Override
    public StatObjectResponse statObject(String bucketName, String objectName) {
        try {
            if (bucketUtils.bucketExists(bucketName)) {
                return minioClient.statObject(StatObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build());
            }
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 判断对象是否存在失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 判断文件夹是否存在
     * @Date: 14:06 2021/3/16
     * @Param: [bucketName, objectName]
     * @return: java.lang.Boolean
     **/
    @Override
    public Boolean doesFolderExist(String bucketName, String objectName) {
        Boolean flag = false;
        try {
            if (bucketUtils.bucketExists(bucketName)) {
                Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).prefix(objectName).recursive(false).build());
                for (Result<Item> result : results) {
                    Item item = result.get();
                    if (item.isDir() && objectName.equals(item.objectName())) {
                        flag = true;
                    }
                }
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * @Author: yongchen
     * @Description: 创建文件夹
     * @Date: 17:58 2021/3/16
     * @Param: [bucketName, folderName]
     * @return: io.minio.ObjectWriteResponse
     **/
    @Override
    public ObjectWriteResponse createFolder(String bucketName, String folderName) {
        try {
            if (bucketUtils.bucketExists(bucketName)) {
                return minioClient.putObject(
                        PutObjectArgs.builder().bucket(bucketName).object(folderName).stream(
                                        new ByteArrayInputStream(new byte[]{}), 0, -1)
                                .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
