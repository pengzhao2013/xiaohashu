package com.quanxiaoha.xiaohashu.auth.domain.dataobject;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDO {
    private Long id;

    private String username;

    private LocalDateTime createTime;

    private LocalDateTime  updateTime;
}