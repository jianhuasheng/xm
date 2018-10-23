package com.xm.xmscapi.web;


import com.xm.xmscapi.bean.AbstractBaseController;
import com.xm.xmscapi.bean.AppContext;
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



/**
 * @author shengjianhua
 */
@Slf4j
@RestController
@RequestMapping("/order")
@Api(tags = "订单")
public class OrderController extends AbstractBaseController {


    @AppAuth
    @PostMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "订单支付")
    public APIResult<Integer> pay(@RequestBody int param) {
        return APIResult.ok(2);
    }


    @ApiOperation(value = "订单列表")
    @AppAuth
    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public APIResult list(@RequestBody int param) {
        int accountId = AppContext.getAccountId();
        return APIResult.ok(2);
    }

    @ApiOperation(value = "订单详情")
    @AppAuth
    @PostMapping(value = "/detail", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public APIResult detail(@RequestBody int param) {
        return APIResult.ok(2);
    }

    @ApiOperation(value = "确认收货")
    @AppAuth
    @PostMapping(value = "/receive", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public APIResult receive(@RequestBody int param) {

        return APIResult.ok(2);
    }

    @ApiOperation(value = "按批次取消订单(只有待付款订单走取消订单流程)")
    @AppAuth
    @PostMapping(value = "/cancelBatch", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public APIResult cancelBatch(@RequestBody int param) {
        return APIResult.ok(2);
    }

    @ApiOperation(value = "付款信息")
    @AppAuth
    @PostMapping(value = "/payInfo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public APIResult payInfo(@RequestBody int param) {

        return APIResult.ok(2);
    }

    @AppAuth
    @PostMapping(value = "confirm", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public APIResult<Integer> confirm(@RequestBody int param) {
        validateOrderConfirm(param);
        return APIResult.ok(2);
    }


    @AppAuth
    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public APIResult<Integer> add(@RequestBody int param) {
        return new APIResult(2);
    }

    /**
     * 验证订单确认参数
     */
    private void validateOrderConfirm(int param) {

    }


    /**
     * 校验订单新增参数
     */
    private void validateOrderAdd(int param) {

    }

}
