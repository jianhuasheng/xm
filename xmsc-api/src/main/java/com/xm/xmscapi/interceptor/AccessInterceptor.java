package com.xm.xmscapi.interceptor;


import com.xm.xmscapi.bean.AppContext;
import com.xm.xmscapi.bean.SimpleUser;
import com.xm.xmscapi.bean.TokenProvider;
import com.xm.xmsccommon.CommonConstant;
import com.xm.xmsccommon.utils.NetWorkUtil;
import com.xm.xmsccommon.utils.TraceLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Component
public class AccessInterceptor extends HandlerInterceptorAdapter {
    private static final String SKELETON_KEY = "201807260101HappyBody_";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("AccessInterceptor prehandle");
        // head请求直接返回
        if ("HEAD".equals(request.getMethod())) {
            return false;
        }

        TraceLogUtil.startTrace();
        SimpleUser user = getUserInfo(request);

        user.setAccountId(123455);
        TraceLogUtil.recordMDC("userId", String.valueOf(user.getAccountId()));
        TraceLogUtil.recordMDC("requestUrl", request.getRequestURI());
        AppContext.setUser(user);
        log.info("AccessInterceptor User AccountId:{}",user.getAccountId());
        return true;
    }

    /**
     * 解析token
     * <p>
     * 建议使用 jwt_token
     *
     * @param request
     * @return
     */
    private SimpleUser getUserInfo(HttpServletRequest request) {
        return getUserInfo(getAccountId(request.getHeader(CommonConstant.TOKEN)), request.getHeader(CommonConstant.VERSION), NetWorkUtil.getRemoteIpAddr(request));
    }

    private int getAccountId(String token) {
        if (token != null && token.startsWith(SKELETON_KEY)) {
            String fakeAccountId = token.replaceAll(SKELETON_KEY, "");
            return Integer.parseInt(fakeAccountId);

        }
        return TokenProvider.getAuthentication(token).getAccountId();
    }


    private SimpleUser getUserInfo(int accountId, String version, String ip) {
        SimpleUser user = new SimpleUser();
        user.setAccountId(accountId);
        user.setVersion(NumberUtils.toFloat(version, 0));
        user.setIp(ip);
        return user;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        TraceLogUtil.stopTrace();
        AppContext.remove();
    }
}
