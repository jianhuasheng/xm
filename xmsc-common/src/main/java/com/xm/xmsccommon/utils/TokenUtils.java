package com.xm.xmsccommon.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Created by liuxl on 2018/4/19
 */
public final class TokenUtils {

    private TokenUtils() {

    }

    /**
     * 通过shopAccountId生成Token
     *
     * @param shopAccountId 用户id
     * @return Token字符串
     */
    public static String generateToken(Integer shopAccountId) {
        return UUID.randomUUID().toString().replaceAll("-", "") + ':' + shopAccountId;
    }

    /**
     * 从Token中解析出shopAccountId
     *
     * @param token Token字符串
     * @return 用户id，解析失败返回0
     */
    public static Integer getShopAccountIdFromToken(String token) {
        if (StringUtils.isBlank(token)) {
            return 0;
        }
        String[] subStr = token.split(":");
        if (subStr.length == 2) {
            String shopAccountId = subStr[1];
            if (StringUtils.isBlank(shopAccountId) || !StringUtils.isNumeric(shopAccountId)) {
                return 0;
            }
            return Integer.valueOf(shopAccountId);
        }
        return 0;
    }

}
