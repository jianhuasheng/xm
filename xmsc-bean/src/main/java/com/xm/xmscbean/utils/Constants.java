package com.xm.xmscbean.utils;

public class Constants
{
    // 1MB
    public static final int ONE_MB_SIZE = 1024 * 1024;

    // 一分钟
    public static final int ONE_MINUTE = 60;

    public static final int ONE_DAY = 60 * 60 * 24;

    // 一秒钟
    public static final int ONE_SECOND = 1000;

    // 最大序列化1MB
    public static final int MAX_BUFFER_SIZE = 1024 * 1024 * 1024;

    // 初始化容量
    public static final int INIT_BUFFER_SIZE = 1024;

    // 常量数字0，一般用于比较。
    public static final int NUMBER_ZERO = 0;

    // memcache 缓存实例个数 (生产机器 是4核)
    public static final int MEMCACHE_INSTANCE_NUM = 2;


    public static final String ONE_CACHE_KEY_DELIMITER = ":";

    // 本地cache占用堆内存比率
    public static final double LOCAL_CACHE_MEM_RATIO = 128 * 1.0 / 4096;

    // 本地cache默认key数量
    public static final long LOCAL_CACHE_DEFAULT_KEY_NUM = 1000L;

    // 预估一个key-value 的大小
    public static final int LOCAL_CACHE_DEFAULT_KEY_SIZE = 10 * 1024; // B

    // 本地缓存默认load加载的值。
    public static final String LOCAL_CACHE_LOAD_DEFAULT_VALUE = "-10086";

    public static final String REDIS_SPLIT_CHAR = ":";
}