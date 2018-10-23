package com.xm.xmscapi.web;

import com.xm.xmscapi.bean.AppContext;
import com.xm.xmscapi.bean.SimpleUser;
import com.xm.xmscapi.bean.annotation.AppAuth;
import com.xm.xmsccommon.bean.APIResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/account")
@Slf4j
@Api(tags = "账号相关API")
public class AccountController {


    @PostMapping(value = "/accountInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取账号信息")
    public Object getInfo() {
        SimpleUser user = AppContext.getUser();
        if (user != null) {
            return APIResult.ok();
        }
        return APIResult.ok();
    }

    @AppAuth(requireLogin = false)
    @PostMapping(value = "login",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "登录")
    public Object login(@RequestBody int param) {
        return APIResult.ok();
    }

    @PostMapping(value = "sendValidateCode", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "发送验证码")
    public APIResult sendValidateCode(@RequestBody Map<String, String> param) {
        String mobileNumber = param.get("mobileNumber");
        String countryCode = param.get("countryCode");
//        accountFacade.sendValidateCode(mobileNumber, countryCode);
        return APIResult.ok();
    }

    @PostMapping(value = "bindMobileByValidateCode", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "通过验证码绑定手机号")
    public Object bindMobileByValidaCode(@RequestBody Map<String, String> param) {
        String mobileNumber = param.get("mobileNumber");
        String countryCode = param.get("countryCode");
        String validateCode = param.get("validateCode");
        return APIResult.ok();
    }

    @PostMapping(value = "bindMobileByWeChat", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "通过微信授权绑定手机号")
    public Object bindMobileByWeChat(@RequestBody int param) {
        return APIResult.ok();
    }

    /**
     * 实名认证接口
     * @param accountAuthParam
     * @return
     */
    @PostMapping("/realName/auth")
    public Object auth(@RequestBody int accountAuthParam) {
        return APIResult.ok();
    }
}
