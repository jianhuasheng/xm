package com.xm.xmsccommon.utils;

import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class UserUtil {
    private UserUtil() {
    }

    @Data
    @Builder
    static class UserInfo {
        private Integer id;
        private String username;
        private String realname;
    }

    private static Map<String, UserInfo> userCache = Maps.newConcurrentMap();
    private static ThreadLocal<String> user = new ThreadLocal<>();

    public static void setCurrentUserInfo(String username, Integer id, String realname) {
        userCache.put(username, UserInfo.builder().id(id).realname(realname).username(username).build());
        if (username != null && !username.equals(user.get())) {
            user.set(username);
        }
    }

    public static String getCurrentUser() {
        if (StringUtils.isEmpty(user.get())) {
            return StringUtils.EMPTY;
        }
        return user.get();
    }

    public static String getCurrentUserName(){
        if (userCache.containsKey(getCurrentUser())) {
            UserInfo userInfo = userCache.get(user.get());
            return userInfo.getUsername();
        }
        return StringUtils.EMPTY;
    }

    public static String getCurrentRealName() {
        if (userCache.containsKey(getCurrentUser())) {
            UserInfo userInfo = userCache.get(user.get());
            return userInfo.getRealname();
        }
        return StringUtils.EMPTY;
    }

    public static Integer getUserId() {
        if (userCache.containsKey(getCurrentUser())) {
            UserInfo userInfo = userCache.get(user.get());
            return userInfo.getId();
        }
        return 0;
    }
}
