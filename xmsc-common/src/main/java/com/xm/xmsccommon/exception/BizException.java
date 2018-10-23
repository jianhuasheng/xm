package com.xm.xmsccommon.exception;

import com.xm.xmsccommon.enums.ErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1240995450014558589L;

    private final ErrorEnum errorEnum;

    public BizException(String message) {
        this(ErrorEnum.SERVER_ERROR, message);
    }

    public BizException(ErrorEnum errorEnum) {
        this(errorEnum, errorEnum.getMessage());
    }

    public BizException(ErrorEnum errorEnum, String message) {
        super(message);
        this.errorEnum = errorEnum;
    }
}
