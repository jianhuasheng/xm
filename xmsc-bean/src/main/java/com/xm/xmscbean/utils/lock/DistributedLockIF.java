package com.xm.xmscbean.utils.lock;

public interface DistributedLockIF
{
    /**
     * 获取锁 不会抛异常
     * @param key
     * @param ttl
     * @return
     */
    boolean acquireLock(String key, int ttl);

    /**
     * 释放锁  不会抛异常
     * @param key
     * @return
     */
    boolean releaseLock(String key);

    /**
     * 获取锁 不会抛异常
     * @param key
     * @param value
     * @return
     */
    boolean acquireLock(String key, String value);
}

