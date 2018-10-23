package com.xm.xmscapi.facade;


import com.xm.xmscapi.bean.SimpleUser;

public interface AccountFacade {
    /**
     * 获取用户信息
     *
     * @param accountId
     * @return
     */
    void getAuthInfo(int accountId);

    /**
     * 判断当前用户是否会员
     *
     * @param user 当前用户
     * @return
     */
    boolean isMember(SimpleUser user);

    /**
     * 登录
     *
     * @param param
     * @return
     */
    void login(int param);

    /**
     * 发送验证码
     *
     * @param mobileNumber
     * @param countryCode
     */
    void sendValidateCode(String mobileNumber, String countryCode);

    /**
     * 验证码验证绑定手机号
     *
     * @param mobileNumber
     * @param countryCode
     * @param validateCode
     * @return
     */
    void bindMobileByValidateCode(String mobileNumber, String countryCode, String validateCode);

    /**
     * 微信授权绑定手机号
     *
     * @param param
     * @return
     */
    void bindMobileByWeChat(int param);

    /**
     * 根据accountId获取微信相关信息
     *
     * @param accountId
     * @return
     */
    void getAccountInfo(int accountId);

    /**
     * 实名认证
     *
     * @param accountAuthParam
     */
    void auth(int accountAuthParam);
}
