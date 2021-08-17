package com.cloud.minio.utils;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveBucketArgs;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 * @version 1.0
 * @description: Minio 存储桶操作
 * @date 2021/6/28 13:37
 */
@Slf4j
@Component
public class BucketUtils {

    @Resource
    private MinioClient minioClient;

    /**
     * @Author: yongchen
     * @Description: 判断bucket是否存在
     * @Date: 11:29 2020/11/12
     * @Param: [bucketName]
     * @return: java.lang.Boolean
     **/
    public boolean bucketExists(String bucketName) {
        if (StringUtils.isBlank(bucketName)){
            return false;
        }
        boolean bucketExists = false;
        try {
            bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 判断桶是否存在失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        if (!bucketExists) {
            log.info(">>>>>>>>>>>>> 判断名为{}的桶不存在 >>>>>>>>>>>>>", bucketName);
        }
        return bucketExists;
    }

    /**
     * @Author: yongchen
     * @Description: 创建桶
     * @Date: 11:55 2020/11/12
     * @Param: [bucketName]
     * @return: void
     **/
    public void makeBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 创建桶失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author: yongchen
     * @Description: 获取桶列表信息
     * @Date: 11:51 2020/11/12
     * @Param: []
     * @return: java.util.List<io.minio.messages.Bucket>
     **/
    public List<Bucket> listBuckets() {
        try {
            List<Bucket> bucketList = minioClient.listBuckets();
            return bucketList;
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 获取桶列表异常！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 根据桶名称删除桶信息
     * @Date: 11:53 2020/11/12
     * @Param: [bucketName]
     * @return: void
     **/
    public void removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 删除桶异常！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
    }
}
