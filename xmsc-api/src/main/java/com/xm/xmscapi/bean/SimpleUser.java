package com.xm.xmscapi.bean;

import lombok.Data;

@Data
public class SimpleUser {
    private int accountId;
    private String openId;
    private float version;
    private String ip;

    private Byte isCommunityManager;
    private Integer communityShopId;
}
