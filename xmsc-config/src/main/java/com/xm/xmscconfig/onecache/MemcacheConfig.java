package com.xm.xmscconfig.onecache;

import com.xm.xmscbean.utils.Constants;
import com.xm.xmscbean.utils.ObjectUtil;
import com.xm.xmscbean.utils.exception.CacheException;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class MemcacheConfig implements Serializable
{
    private static final long serialVersionUID = -6860025755120388L;

    private String prefix = "";

    /***
     * 服务器地址
     */
    private String host;

    /***
     * 端口
     */
    private int port;

    public MemcacheConfig()
    {
    }

    public MemcacheConfig(String filePath)
    {
        // 初始化属性
        initProperty(filePath);
    }

    private void initProperty(String filePath)
    {
        Properties prop = new Properties();
        try
        {
            // 加载配置文件
            prop.load(this.getClass().getResourceAsStream(filePath));
        }
        catch (IOException e)
        {
            throw new CacheException("MemcacheConfig Initialization error", e);
        }
        if (prop.isEmpty())
        {
            throw new CacheException("properties is null or no attribute");
        }
        this.prefix = prop.getProperty("prefix", "");
        this.host = prop.getProperty("host");
        this.port = Integer.valueOf(prop.getProperty("port"));
        checkConfigProperty();
    }

    /***
     * 校验参数，不合法返回运行异常。
     */
    private void checkConfigProperty()
    {
        if (ObjectUtil.isNullOrEmptyStr(host))
        {
            throw new CacheException("host must be not null or empty");
        }
        if (Constants.NUMBER_ZERO >= port)
        {
            throw new CacheException("port must be greater than 0");
        }
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MemcacheConfig that = (MemcacheConfig)o;

        if (port != that.port)
            return false;
        if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null)
            return false;
        return host != null ? host.equals(that.host) : that.host == null;

    }

    @Override
    public int hashCode()
    {
        int result = prefix != null ? prefix.hashCode() : 0;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + port;
        return result;
    }
}

