package com.xm.xmsccommon.utils;

import java.util.Optional;

/**
 * 字符串相关
 */
public final class StringUtil
{
    /**
     * 判断当前字符串是否为空（null和空字符串都返回true）
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str)
    {
        return (str == null || "".equals(str));
    }

    /**
     * 判断当前字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(Object str)
    {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空白字符串（null、""、" "、"   "字符都返回true）
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(final CharSequence cs)
    {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0)
        {
            return true;
        }
        for (int i = 0; i < strLen; i++)
        {
            if (!Character.isWhitespace(cs.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空白字符串
     *
     * @param cs
     * @return
     */
    public static boolean isNotBlank(final CharSequence cs)
    {
        return !isBlank(cs);
    }


    /**
     * 如果obj为null, 则返回defVal, 否则返回原值
     *
     * @param obj
     * @param defVal
     * @param <T>
     * @return
     */
    public static <T> T defaultIfNull(T obj, T defVal)
    {
        return Optional.ofNullable(obj).orElse(defVal);
    }

    /**
     * 判断字符串，是否是数字
     *
     * @param cs
     * @return
     */
    public static boolean isNumeric(CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        } else {
            int sz = cs.length();

            for(int i = 0; i < sz; ++i) {
                if (!Character.isDigit(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }
}