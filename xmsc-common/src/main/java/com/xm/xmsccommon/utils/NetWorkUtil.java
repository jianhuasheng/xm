package com.xm.xmsccommon.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网络工具类
 * <pre class="code">
 *     NetUtil.getFirstLanIp
 *     NetUtil.getFirstWanIp
 *     NetUtil.isIPv4
 *     NetUtil.isLoopBackIp
 *     NetUtil.isLanIp
 *     NetUtil.isWanIp
 *     NetUtil.getRemoteIpAddr
 * </pre>
 */
public final class NetWorkUtil
{
    /**
     * 获取本机IP
     *
     * @return string or null
     */
    public static String getLocalHostIP()
    {
        try
        {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
     * 获取本机主机名
     *
     * @return string or null
     */
    public static String getLocalHostName()
    {
        try
        {
            return InetAddress.getLocalHost().getHostName();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 本机第一个局域网地址
     *
     * @return string or null
     */
    public static String getFirstLanIp()
    {
        try
        {
            Map<NetworkInterface, List<InterfaceAddress>> allIpMap = getAllIp();
            for (List<InterfaceAddress> addressList : allIpMap.values())
            {
                for (InterfaceAddress address : addressList)
                {
                    String ip = address.getAddress().getHostAddress();
                    if (StringUtil.isNotBlank(ip) && NetWorkUtil.isLanIp(ip))
                    {
                        return ip;
                    }
                }
            }
        }
        catch (SocketException e)
        {
            ;
        }
        return null;
    }

    /**
     * 本机第一个广域网地址
     *
     * @return string or null
     */
    public static String getFirstWanIp()
    {
        {
            try
            {
                Map<NetworkInterface, List<InterfaceAddress>> allIpMap = getAllIp();
                for (List<InterfaceAddress> addressList : allIpMap.values())
                {
                    for (InterfaceAddress address : addressList)
                    {
                        String ip = address.getAddress().getHostAddress();
                        if (StringUtil.isNotBlank(ip) && NetWorkUtil.isWanIp(ip))
                        {
                            return ip;
                        }
                    }
                }
            }
            catch (SocketException e)
            {
                ;
            }
            return null;
        }
    }

    /**
     * isIPv4
     *
     * @param ip
     * @return Boolean
     */
    public static Boolean isIPv4(String ip)
    {
        if (StringUtil.isBlank(ip) || ip.length() > 15 || !ip.contains("."))
        {
            return false;
        }

        String[] ipArr = ip.split("\\.");
        if (ipArr.length != 4)
        {
            return false;
        }

        try
        {
            for (int i = 0; i < 4; i++)
            {
                if ((Integer.valueOf(ipArr[i]) < 0) || (Integer.valueOf(ipArr[i]) > 255))
                {
                    return false;
                }
            }
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * 是否本地回传地址
     *
     * @param ip
     * @return Boolean
     */
    public static Boolean isLoopBackIp(String ip)
    {
        return "127.0.0.1".equals(ip);
    }

    /**
     * 判断是否局域网ip
     * <p>
     * A类 10.0.0.0--10.255.255.255 B类 172.16.0.0--172.31.255.255 C类 192.168.0.0--192.168.255.255
     *
     * @return Boolean
     */
    public static Boolean isLanIp(String ip)
    {
        if (!isIPv4(ip))
        {
            return false;
        }

        String[] ipArr = ip.split("\\.");

        return ip.startsWith("10.") || ip.startsWith("192.168.") || (ip.startsWith("172.") && (Integer.valueOf(ipArr[1]) >= 16 && Integer.valueOf(ipArr[1]) <= 31));
    }

    /**
     * 判断是否广域网ip
     *
     * @param ip
     * @return
     */
    public static Boolean isWanIp(String ip)
    {
        return isIPv4(ip) && !isLanIp(ip) && !isLoopBackIp(ip);
    }

    /**
     * 获取所有网卡信息
     *
     * @return
     * @throws SocketException
     */
    private static Map<NetworkInterface, List<InterfaceAddress>> getAllIp() throws SocketException
    {
        Map<NetworkInterface, List<InterfaceAddress>> interfaceMap = new HashMap<>();

        Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaceEnumeration.hasMoreElements())
        {
            NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();
            if (networkInterface.isUp())
            {
                interfaceMap.put(networkInterface, networkInterface.getInterfaceAddresses());
            }
        }
        return interfaceMap;
    }

    public static String getRemoteIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.length() > 15)
        {
            if (ip.indexOf(",") > 0)
            {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    public static String long2ip(long i)
    {
        return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + (i & 0xFF);
    }

    public static long ip2long(String ip)
    {
        if (StringUtil.isBlank(ip))
        {
            return 0;
        }
        if (!isIPv4(ip))
        {
            return 0;
        }

        String[] addrArr = ip.split("\\.");

        long num = 0;

        for (int i = 0; i < addrArr.length; i++)
        {
            int power = 3 - i;
            num += ((Integer.parseInt(addrArr[i]) % 256 * Math.pow(256, power)));
        }

        return num;
    }
}