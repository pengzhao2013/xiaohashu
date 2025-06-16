package com.quanxiaoha.xiaohashu.search.service;

import org.springframework.http.ResponseEntity;

/**
 * 拓展词典
 *
 * @Author : zpstart
 * @Date: 2025-06-16 13:19
 */
public interface ExtDictService {
    /**
     * 获取热更新词典
     * @return
     */
    ResponseEntity<String> getHotUpdateExtDict();
}