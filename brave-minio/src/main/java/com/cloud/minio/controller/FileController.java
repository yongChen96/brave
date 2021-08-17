package com.cloud.minio.controller;

import com.cloud.brave.core.base.controller.BaseController;
import com.cloud.brave.core.result.Result;
import com.cloud.minio.service.FileService;
import io.minio.ObjectWriteResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yongchen
 * @description: 附件控制器
 * @date 2021/8/13 17:37
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Api(value = "FileController", tags = "附件控制器")
public class FileController extends BaseController {

    private final FileService fileService;

    /**
     * @param bucketName
     * @param file
     * @description: 附件上传
     * @return: com.cloud.brave.core.result.Result<java.lang.String>
     * @author yongchen
     * @date: 2021/8/17 14:53
     */
    @PostMapping("/upload")
    @ApiOperation(value = "附件上传", notes = "附件上传")
    public Result<String> upload(@RequestParam(value = "bucketName", required = false) String bucketName,
                                 @RequestBody MultipartFile file) {
        try {
            fileService.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), file.getSize(), file.getContentType());
            String objectUrl = fileService.getObjectUrl(bucketName, file.getOriginalFilename(), 0);
            if (StringUtils.isNotBlank(objectUrl)) {
                return success(StringUtils.substringBefore(objectUrl, "?"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return failed("附件上传失败");
    }

    /**
     * @description: 附件下载
     * @param: * @param objectName
     * @return: com.cloud.brave.core.result.Result<java.io.InputStream>
     * @author yongchen
     * @date: 2021/8/16 14:00
     */
    @GetMapping("/download")
    @ApiOperation(value = "附件下载", notes = "附件下载")
    public Result<InputStream> download(@RequestParam String objectName,
                                        @RequestParam(value = "bucketName", required = false) String bucketName) {
        InputStream inputStream = fileService.getObject(bucketName, objectName);
        return success(inputStream);
    }

    /**
     * @param objectName
     * @param duration
     * @description: 获取附件分享链接
     * @param: bucketName
     * @return: com.cloud.brave.core.result.Result<java.lang.String>
     * @author yongchen
     * @date: 2021/8/16 14:21
     */
    @GetMapping("/getObjectUrl")
    @ApiOperation(value = "获取附件分享链接", notes = "获取附件分享链接")
    public Result<String> getObjectUrl(@RequestParam String bucketName, @RequestParam String objectName, @RequestParam int duration) {
        String objectUrl = fileService.getObjectUrl(bucketName, objectName, duration);
        if (StringUtils.isNotBlank(objectUrl)) {
            return success(objectUrl);
        }
        return failed("获取分享链接失败");
    }
}
