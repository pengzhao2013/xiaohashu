package com.quanxiaoha.xiaohashu.oss.biz.service;

import com.quanxiaoha.framework.common.response.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-05 12:20
 */
public interface FileService {
    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    Response<?> uploadFile(MultipartFile file);
}
