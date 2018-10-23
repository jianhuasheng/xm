package com.xm.xmscapi.bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractBaseController {

    protected float getVersion() {
        return AppContext.getUser().getVersion();
    }

}
