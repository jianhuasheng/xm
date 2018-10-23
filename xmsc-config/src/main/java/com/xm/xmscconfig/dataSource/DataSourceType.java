package com.xm.xmscconfig.dataSource;

import lombok.Getter;

@Getter
public enum DataSourceType {
    READ("read", "读库"),
    WRITE("write", "写库"),;
    private String type;
    private String desc;

    DataSourceType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
