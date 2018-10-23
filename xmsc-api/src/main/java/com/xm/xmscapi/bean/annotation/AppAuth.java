package com.xm.xmscapi.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * controller 注解, 表示接口必须有权限才能调用
 */
@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface AppAuth {
    /**
     * 默认必须登录
     */
    boolean requireLogin() default true;

    /**
     * 角色权限, 默认无权限
     * 只有需要登录的接口才会判断角色
     *
     */
    RoleEnum requireRole() default RoleEnum.NONE;
}
