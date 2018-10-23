package com.xm.xmscapi.web;

import com.xm.xmsccommon.bean.APIResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺
 *
 * @author LiuGaoJian
 * @version 1.0 2018/4/26
 */
@RestController
@RequestMapping("/api/shop")
@Slf4j
public class ShopController {


    @RequestMapping("hello")
    @Description("店铺列表")
    public Object listByPage() {
        System.out.println("shengjianhau");
        return APIResult.ok();
    }


}
