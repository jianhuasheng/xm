package com.xm.xmscconfig.onecache;
import com.xm.xmscbean.utils.serial.StringSerializer;
import com.xm.xmscbean.utils.Constants;
import com.xm.xmscbean.utils.ObjectUtil;
import com.xm.xmscbean.utils.serial.Serializer;
/**
 * 序列化, 前缀
 * Created by shengjianhau.
 */
public interface BaseCacheClientIF
{
    // 序列化
    public static final  Serializer stringSerializer = new StringSerializer();

    byte[] serializeKey(Object key);

    <T> T deserializeKey(byte[] byteKey);

    byte[] serializeValue(Object value);

    <T> T deserializeValue(byte[] byteValue);

    // 前缀
    String getPrefix();

    default String getFullKey(String key)
    {
        String prefix = getPrefix();
        if (ObjectUtil.isNullOrEmptyStr(prefix) || ObjectUtil.isNullOrEmptyStr(key))
        {
            return key;
        }

        return String.join(Constants.ONE_CACHE_KEY_DELIMITER, new String[]{prefix, key});
    }

    default String removeKeyPrefix(String fullKey)
    {
        String prefix = getPrefix();
        if (ObjectUtil.isNullOrEmptyStr(prefix) || ObjectUtil.isNullOrEmptyStr(fullKey))
        {
            return fullKey;
        }
        return fullKey.replaceFirst(prefix + Constants.ONE_CACHE_KEY_DELIMITER, "");
    }
}
