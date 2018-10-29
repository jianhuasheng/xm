package com.xm.xmscbean.utils;

import java.util.List;

/***
 * 自定义一些工具方法
 *
 * @author luyanjun
 *
 */
public class ObjectUtil
{

    /**
     * 判断Object对象是否为null
     *
     * @param object
     * @return
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    /**
     * 判断Object对象是否为null或者""
     * @param str
     * @return
     */
    public static boolean isNullOrEmptyStr(Object str)
    {
        return (str == null || "".equals(str));
    }

    /***
     * 给集合类用的
     *
     * @param keys
     * @return
     */
    public static boolean isNullOrEmpty(List<String> keys)
    {
        return keys == null || keys.size() == 0;
    }
}
