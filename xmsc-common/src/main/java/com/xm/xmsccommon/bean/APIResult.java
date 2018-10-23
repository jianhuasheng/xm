package com.xm.xmsccommon.bean;

import com.xm.xmsccommon.enums.ErrorEnum;
import lombok.Data;

@Data
public class APIResult<T> {
    private int status = 1; // 0:失败, 1:成功, 2:提示升级, 3:强制升级, 4:强制退出
    private int errorCode = 0; // 为0表示没有错误
    private T data; // 数据
    private String errorMessage; // 错误描述或提示消息

    public APIResult() {
    }

    public APIResult(T data) {
        this.data = data;
    }

    public APIResult(int status, int errorCode, String errorMessage) {
        this(status, errorCode, null, errorMessage);
    }

    public APIResult(int status, int errorCode, T data, String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public static APIResult failResult(ErrorEnum errorEnum) {
        return new APIResult(errorEnum.getAppResultStatus(), errorEnum.getCode(), errorEnum.getMessage());
    }

    public static APIResult failResult(ErrorEnum errorEnum, String message) {
        return new APIResult(errorEnum.getAppResultStatus(), errorEnum.getCode(), message);
    }

    public static APIResult failResult(String message) {
        return failResult(ErrorEnum.SERVER_ERROR, message);
    }

    public static <T> APIResult<T> ok(T data) {
        return new APIResult<>(data);
    }

    public static APIResult ok() {
        return new APIResult();
    }
}
