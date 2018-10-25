package com.xm.xmscapi.web;

import com.xm.BugService;
import com.xm.xmsccommon.bean.APIResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 店铺
 *
 * @author ShengJianhua
 */
@RestController
@RequestMapping("/api/shop")
@Slf4j
public class ShopController {
    @Resource
    private BugService bugService;

    @RequestMapping("hello")
    @Description("店铺列表")
    public Object listByPage() {
        System.out.println("shengjianhau");
        bugService.insertBug();
        return APIResult.ok();
    }


}
