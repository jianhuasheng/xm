package com.xm.xmsccommon.utils;

import com.xm.xmsccommon.enums.ErrorEnum;
import com.xm.xmsccommon.exception.BizException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

public class BusinessAssert {
    private BusinessAssert() {
    }

    public static <T> T notNull(T obj, ErrorEnum errorEnum) {
        isTrue(null != obj, errorEnum);
        return obj;
    }

    public static void isNull(Object obj, ErrorEnum errorEnum) {
        isTrue(null == obj, errorEnum);
    }

    public static String nonBlank(String str, ErrorEnum errorEnum) {
        isTrue(StringUtils.isNotBlank(str), errorEnum);
        return str;
    }

    /**
     * 假设不存在空数据
     *
     * @param objs      对象列表
     * @param errorEnum 错误码
     */
    public static void nonNull(ErrorEnum errorEnum, Object... objs) {
        isFalse(Arrays.stream(objs).anyMatch(Objects::isNull), errorEnum);
    }

    /**
     * 假设不存在空字符串
     *
     * @param strs      字符串列表
     * @param errorEnum 错误码
     */
    public static void nonBlank(ErrorEnum errorEnum, String... strs) {
        if (Arrays.stream(strs).anyMatch(StringUtils::isAnyBlank)) {
            throw new BizException(errorEnum);
        }
    }

    public static void isTrue(boolean flag, ErrorEnum errorEnum) {
        isTrue(flag, errorEnum, null);
    }

    public static void isTrue(boolean flag, ErrorEnum errorEnum, String msg) {
        if (!flag) {
            if (msg == null) {
                throw new BizException(errorEnum);
            } else {
                throw new BizException(errorEnum, msg);
            }
        }
    }

    public static void isFalse(boolean flag, ErrorEnum errorEnum) {
        isTrue(!flag, errorEnum);
    }

    public static void isFalse(boolean flag, ErrorEnum errorEnum, String msg) {
        isTrue(!flag, errorEnum, msg);
    }
}
