package com.xm.xmscbean.utils.serial;

public interface Serializer<T>
{
    /**
     * @param t
     * @return
     */
    byte[] serialize(T t);

    /**
     * @param bytes
     * @return
     */
    T deserialize(byte[] bytes);
}
