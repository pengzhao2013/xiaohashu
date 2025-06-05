package com.quanxiaoha.xiaohashu.oss.biz.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件策略接口
 * @author zpstart
 * @version 1.0
 * @date 2025-06-05 9:52
 */
public interface FileStrategy {
    /**
     * 文件上传
     *
     * @param file
     * @param bucketName
     * @return
     */
    String uploadFile(MultipartFile file, String bucketName);
}
