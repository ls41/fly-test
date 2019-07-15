package com.lsy.wechat.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserToken {
    private Long userId;
    private String token;
    private String sessionKey;
    private LocalDateTime expireTime;
    private LocalDateTime updateTime;
}
