package com.xm.xmsccommon.utils;

public final class SwitchHelper {
    public SwitchHelper() {
    }

    public static boolean securityDatasourceEnable() {
        return "false".equals(System.getenv("security_datasource_skip"));
    }
}