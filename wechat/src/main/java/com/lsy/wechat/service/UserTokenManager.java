package com.lsy.wechat.service;


import com.lsy.wechat.domain.UserToken;
import com.lsy.wechat.utils.CharUtil;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 维护用户token
 */
public class UserTokenManager {
    private static Map<String, UserToken> tokenMap = new ConcurrentHashMap<>();
    private static Map<Long, UserToken> idMap = new ConcurrentHashMap<>();

    public static Long getUserId(String token) {
        UserToken userToken = tokenMap.get(token);
        if (userToken == null) {
            return null;
        }

        if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            tokenMap.remove(token);
            idMap.remove(userToken.getUserId());
            return null;
        }

        return userToken.getUserId();
    }


    public static UserToken generateToken(Long id) {
        UserToken userToken;

//        userToken = idMap.get(id);
//        if(userToken != null) {
//            tokenMap.remove(userToken.getToken());
//            idMap.remove(id);
//        }

        String token = CharUtil.getRandomString(32);
        while (tokenMap.containsKey(token)) {
            token = CharUtil.getRandomString(32);
        }

        LocalDateTime update = LocalDateTime.now();
        LocalDateTime expire = update.plusDays(1);

        userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUpdateTime(update);
        userToken.setExpireTime(expire);
        userToken.setUserId(id);
        tokenMap.put(token, userToken);
        idMap.put(id, userToken);

        return userToken;
    }

    public static String getSessionKey(Long userId) {
        UserToken userToken = idMap.get(userId);
        if (userToken == null) {
            return null;
        }

        if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            tokenMap.remove(userToken.getToken());
            idMap.remove(userId);
            return null;
        }

        return userToken.getSessionKey();
    }

    public static void removeToken(Long userId) {
        UserToken userToken = idMap.get(userId);
        String token = userToken.getToken();
        idMap.remove(userId);
        tokenMap.remove(token);
    }
}
