package com.xm.xmscapi.interceptor;


import com.xm.xmscapi.bean.AppContext;
import com.xm.xmscapi.bean.SimpleUser;
import com.xm.xmscapi.bean.annotation.AppAuth;
import com.xm.xmsccommon.enums.ErrorEnum;
import com.xm.xmsccommon.utils.BusinessAssert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

    @Resource
    private Environment env;


    @Value("${white.urls}")
    private String whiteUrls;

    private List<String> whiteList;

    private Map<String, Pattern> whitePatternMap = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("AuthInterceptor prehandle");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        AppAuth annotation=method.getAnnotation(AppAuth.class);
        boolean requireLogin=false;
        if(annotation!=null){
            requireLogin=annotation.requireLogin();
        }
        if(!requireLogin){
            return true;
        }

        String requestURI=request.getServletPath();
        if(checkWhiteUrl(requestURI)){
            return true;
        }

        SimpleUser simpleUser=AppContext.getUser();
        BusinessAssert.isTrue(simpleUser.getAccountId()==123455,ErrorEnum.COFFER_INCOME_AMOUNT_ERROR);
        if(simpleUser.getAccountId()==123455){
            log.info("good");
        }else{
            log.info("bad");
        }



        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        whiteList = Arrays.asList(whiteUrls.trim().split(";"));
    }

    /**
     * 白名单校验
     * @param url
     * @return
     */
    private boolean checkWhiteUrl(String url) {
        if (whiteList != null && whiteList.contains(url)) {
            return true;
        }
        if (CollectionUtils.isNotEmpty(whiteList)) {
            Pattern patternUrl = null;
            Matcher matcher = null;
            for (String strUrl : whiteList) {
                if (!strUrl.contains("*")) {
                    continue;
                }
                patternUrl = whitePatternMap.get(strUrl);
                if (patternUrl == null) {
                    patternUrl = Pattern.compile(strUrl);
                    whitePatternMap.putIfAbsent(strUrl, patternUrl);
                }

                matcher = patternUrl.matcher(url);
                if (matcher.find()) {
                    return true;
                }
            }
        }

        return false;
    }
}
