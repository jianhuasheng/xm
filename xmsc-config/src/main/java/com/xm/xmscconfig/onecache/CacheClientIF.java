package com.xm.xmscconfig.onecache;


import com.xm.xmscbean.utils.exception.CacheException;
import com.xm.xmscbean.utils.lock.DistributedLockIF;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 缓存产品接口类
 *
 */
public interface CacheClientIF extends BaseCacheClientIF, DistributedLockIF
{
    /**
     * 根据Key,获得value ，参数不能为空否则报异常(runtimeException)。
     *
     * <pre>
     * localCache 本地缓存在这个地方有个坑，如果key不存在，默认的底层返回不能为空，否则会报异常，我们认为，key不存在返回null
     * 很正常，所以，实现类里面，重写了他们的load方法，如果发现返回的是-10086，我们认为是value不存在，然后转换为 null，客户端使用时需要做对get到的值进行非空判断。
     *
     * memcached 这里需要注意一点，底层序列化是用的kryo，性能是jdk默认的序列化的7倍左右。目前kryo在运行较长时间时，可能会造成
     * OOM，如果发现，请业务方先检查这个，二期会对kryo进行优化，使用kryo的池，用来控制对象的生产和销毁。
     *
     * redis redis也是使用的kryo进行的反序列化，存在OOM的可能性，所以调用缓存也请不要for循环get单个key，请使用下方的批 量查询。
     * </pre>
     *
     * @param key
     * @return T 返回值可能是null，需要客户端自己进行判断。
     * @throws CacheException
     */
    <T> T get(String key);

    byte[]  getRawBytes(String key);

    /**
     * 获取incr或decr的之后的值,切记不能使用get方法,一定要使用此方法
     *
     * <pre>
     * localCache 不支持此方法
     *
     * memcached 支持
     *
     * redis  支持
     * </pre>
     *
     * @param key
     * @return Long
     * @throws CacheException
     */
    Long getLong(String key);

    /****
     * 先获取，如果不存在，就set 。函数式编程
     *
     * @param key
     * @param supplier
     * @param expireTime
     * @return
     * @throws CacheException
     */
    <T> T getOrCreate(String key, Supplier<T> supplier, int expireTime);

    <T> T getOrCreate(String key, Supplier<T> supplier);

    <T> T getOrCreateWithSyncLock(String key, Supplier<T> supplier, int expireTime);

    /**
     * 根据key集合批量获取
     *
     * <pre>
     * localCache 这里如果key不存在的，map里面的value存放的是null，key肯定还是原来的key，使用者需要明确知道。
     *
     * memcached  支持
     *
     * redis
     * </pre>
     *
     * @param keys
     * @return Map<String,Object>  如果key不存在,会返回null
     * @throws CacheException
     */
    Map<String, Object> getAllByKeys(List<String> keys);

    /**
     * 放入缓存的方法
     *
     * <pre>
     * localCache 本地缓存实现这个方法，需要注意使用的是guava的本地缓存，缓存实例最大过期时间10分钟，jar控制，不支持客户端修改。
     * 支持客户端传入单个key的缓存时间，单位是秒。三个参数都不能为空，否则报runtimeException异常。second参数必 须大于0。
     *
     * memcached  value < 1MB
     *
     * redis  支持
     * </pre>
     *
     * @param key
     * @param value
     * @param second  秒
     */
    void put(String key, Object value, int second);

    /**
     * bytes 不会再序列化
     * 需要通过getRawBytes获取
     * @param key
     * @param bytes
     * @param second
     */
    void putBytes(String key, byte[] bytes, int second);

    /**
     * 根据key删除一个值
     *
     * <pre>
     * localCache 支持
     *
     * memcached 支持
     *
     * redis   支持
     * </pre>
     *
     * @param key
     * @return boolean
     * @throws CacheException
     */
    boolean delete(String key);

    /**
     * 删除多个key
     *
     * <pre>
     * localCache   正常返回true，没有需要特别注意的。
     *
     * memcached    不支持
     *
     * redis        支持,并且是原子性,线程安全
     * </pre>
     *
     * @return boolean
     * @throws CacheException
     */
    boolean delete(List<String> keys);

    /**
     * 加法
     *
     * <pre>
     * localCache   不支持此方法。
     *
     * memcached    支持
     *
     * redis        支持
     * </pre>
     *
     * @param key
     * @param delta
     * @return long 返回加之后的值
     * @throws CacheException
     */
    long incr(String key, long delta);

    /**
     * 减法
     *
     * <pre>
     * localCache   不支持此方法。
     *
     * memcached    最小值为0(因为memcached底层是无符号64位数字)
     *
     * redis        最小值可能为负数(因为redis底层是有符号64位数字)
     * </pre>
     *
     * @param key
     * @param decrement
     * @return long 返回减之后的值
     * @throws CacheException
     */
    long decr(String key, int decrement);

    /**
     * 获取缓存的有效时间 (单位毫秒)
     * memcached暂时不支持
     *
     * @param key       缓存key
     * @return          有效时间
     *  -1：没有设置过期时间
     *  -2：缓存不存在、已过期、无法获取缓存时间
     */
    long pttl(String key);
}