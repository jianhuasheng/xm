package com.xm.xmscapi.bean;

/**
 * 注意只能在主线程中使用,
 * 如果是异步或线程池, 不可调用
 */
public class AppContext {
    private static ThreadLocal<SimpleUser> users = new ThreadLocal<>();

    private AppContext() {
    }

    public static void setUser(SimpleUser user) {
        users.set(user);
    }

    public static SimpleUser getUser() {
        return users.get();
    }

    public static void remove() {
        users.remove();
    }

    public static int getAccountId() {
        SimpleUser user = getUser();
        if (user == null) {
            return 0;
        }
        return user.getAccountId();
    }

}
