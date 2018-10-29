package com.xm.xmscbean.utils.serial;

import java.nio.charset.Charset;

/**
 * Created by lijianwei on 17/6/2.
 */
public class StringSerializer implements Serializer<String>
{
    private static final Charset UTF8_CHARSET = Charset.forName("UTF8");

    @Override
    public byte[] serialize(String s)
    {
        return (s == null ? null : s.getBytes(UTF8_CHARSET));
    }

    @Override
    public String deserialize(byte[] bytes)
    {
        return (bytes == null ? null : new String(bytes, UTF8_CHARSET));
    }
}
