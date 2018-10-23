package com.xm.xmsccommon.enums;

import lombok.Getter;

/**
 * 错误码
 */
@Getter
public enum ErrorEnum {
    OK(0, "成功", 1),

    // ----------------------- 系统级错误 -----------------------
    SYSTEM_MAINTENANCE(10000, "系统维护"),
    SYSTEM_ERROR(10001, "系统错误"),
    SERVER_ERROR(10002, "未知业务异常"),
    VERSION_NEED_UPGRADE(10003, "版本太低需升级", 2),
    VERSION_NEED_FORCE_UPGRADE(10003, "版本太低需升级", 3),
    TOO_MANY_REQUEST(10004, "请求太频繁"),
    PARAM_ERROR(10005, "参数错误"),
    RESOURCE_NOT_EXIST(10006, "接口不存在"),
    IP_TOO_MANY_REQUEST(10007, "IP请求频次超过上限"),
    ACCOUNT_TOO_MANY_REQUEST(10008, "用户请求频次超过上限"),
    ACCOUNT_NOT_EXIST(10009, "不存在的用户"),
    ACCOUNT_NOT_LOGIN(10010, "未登录"),
    TOKEN_ERROR(10011, "token验证失败"),
    VALID_CODE_ERROR(10012, "验证码错误"),
    NO_SHOP_ROLE(10013, "没有店主权限"),
    NO_PARTNER_ROLE(10014, "没有合伙人权限"),
    UPLOAD_FILE_NOT_EXIST(10015, "请上传符合条件的文件"),
    INVALID_PHONE_NUMBER(10016, "手机号格式不正确"),
    ACCESS_DENIED(10017, "手机号格式不正确"),
    VALID_CHAR(10018, "不允许输入特殊表情字符"),
    VALID_REQUEST(10019, "非法的请求"),
    VERSION_LOW(10020, "请升级到最新版本再进行操作，谢谢！"),
    INVALID_PHOTO(10021, "图片涉及违规信息，请重新上传"),
    INVALID_TEXT(10022, "文本涉及违规信息，请重试"),


    // ----------------------- 业务及错误 -----------------------
    // 会员
    ACCOUNT_NOT_MEMBER(20001, "仅限捕手会员使用"),
    ACCOUNT_NOT_A_MEMBER(20002, "该用户不是会员"),
    // 店铺
    USERNAME_OR_PASSWORD_ERROR(20101, "用户名或密码错误"),
    USER_LOCK_ERROR(20102, "账号已锁定，请联系商家运营！"),

    //订单
    ORDER_NOT_EXISTS(20201, "订单不存在"),
    ORDER_STATUS_ERROR(20202, "错误的订单状态"),
    ORDER_PAY_ERROR(20203, "订单支付失败"),
    ORDER_NO_PAYMARK(20204, "无payMark信息"),
    ORDER_MEMBER_SNAPSHOT(20205, "订单会员关系快照失败"),
    ORDER_NOT_SUPPORT_REFUND(20206, "订单不支持退款"),
    INVALID_PRODUCTS(20207, "部分商品已下架或库存不足，请重新选购"),
    INVALID_ADDRESS(20208, "无效的地址"),
    INVALID_DELIVERY_AREA(20209, "不在配送范围"),
    INVALID_CONFIRM_NUMBER(20210, "无效的订单确认编号"),
    ORDER_NOT_HAVE_PAY(20211, "订单未支付不能发货"),
    ORDER_ADD_THREADHOLD(20212, "订单金额需不小于1元"),

    ORDER_BATCH_SEND_EMPTY(20213, "文件内无数据"),
    ORDER_BATCH_SEND_CONFIRM_WITH_ERROR(20214, "确认导入存在失败的数据"),
    ORDER_MIN_PRICE(20215, "消费金额需大于0元"),
    ORDER_EXPORT_CAN_NOT_CANCEL(20216, "订单已导出，不能取消"),
    ORDER_EXPORT_CAN_NOT_CANCEL_FRONT(20217, "当前订单状态不允许取消订单，请刷新页面。"),
    ORDER_THRESHOLD_AMOUNT_ERROR(20218, "未达到起送金额"),

    ORDER_TIMEOUT_CANNOT_PAY(20219, "订单已超时取消，无法支付"),
    ORDER_EXPORT_EXCEED_LIMIT(20220, "订单导出条数超过6000条"),


    //收货地址
    RECEIVE_ADDRESS_CONVERT_ERROR(20301, "类型转换失败"),
    ADDRESS_NOT_FOUND(20304, "未找到符合要求的城市banner"),
    ADDRESS_DELETE_USER_ILLEGAL(20305, "您不能删除别人的收货地址"),
    ADDRESS_FULL_NAME_MAX_LENGTH_ERROR(20306, "名字长度超过限制"),
    ADDRESS_ADDRESS_NAME_MAX_LENGTH_ERROR(20307, "地址名称长度超过限制"),
    ADDRESS_ADDRESS_DETAIL_MAX_LENGTH_ERROR(20308, "详细地址长度超过限制"),

    // 资产
    COFFER_CAN_WITHDRAW_AMOUNT_NOT_ENOUGH(20401, "可提现金额不足"),
    COFFER_WITHDRAW_AMOUNT_NOT_SATISFIED(20402, "提现金额不符合要求"),
    COFFER_WITHDRAW_FAILED(20403, "提现失败,请稍后再试"),
    COFFER_INCOME_AMOUNT_ERROR(20404, "收入金额错误"),
    COFFER_INCOME_FAILED(20405, "增加收入失败"),
    COFFER_WITHDRAW_APPLY_AMOUNT_TOO_LOW(20406, "提现金额不能低于100元"),
    COFFER_SETTLEMENT_CAN_NOT_BE_NULL(20407, "无法提现，不存在店铺的结算信息"),
    COFFER_WITHDRAW_NOT_EXIST(20408, "不存在打款id对应的打款单"),
    COFFER_WITHDRAW_SET_PAID_FAILED(20409, "设为已打款失败"),
    COFFER_EXCEL_READ_FAILED(20410, "excel解析失败，请使用模板文件格式"),
    COFFER_EXCEL_CONTENT_EMPTY(20411, "不存在批量已打款记录，请检查excel内容"),
    COFFER_EXCEL_WITHDRAW_AMOUNT_NOT_EQUAL(20412, "提现金额不一致"),
    COFFER_EXCEL_FAIL_RST_NOT_EXIST(20413, "退款错误信息不存在"),
    COFFER_REMIT_DUPLICATE(20414, "该提现记录已存在打款记录"),
    COFFER_ABNORMAL(20415, "账户资产异常，请联系客服"),


    //退款
    REFUND_FINISHED_ORDER_TIME_OUT(20501, "订单交易完成已超过72小时，无法退款"),
    REFUND_UNSATISFIED_ORDER_STATUS(20502, "当前订单状态无法申请退款"),
    REFUND_UNSATISFIED_AFTER_SALE_STATUS(20503, "当前订单已申请售后退款"),
    REFUND_AMOUNT_EXCEED_REAL_PRICE(20504, "退款金额不得超过订单实付金额"),
    REFUND_APPLYING_REFUND_NOT_EXIST(20505, "无退款中的售后记录"),
    REFUND_CURRENT_STATUS_CAN_NOT_CANCEL(20506, "当前退款状态无法取消退款"),
    REFUND_REASON_NOT_EXIST(20507, "请选择退款理由"),
    REFUND_APPLY_AMOUNT_NOT_QUALIFIED(20508, "请输入正确的退款金额"),
    REFUND_ORDER_NOT_EXIST(20509, "订单不存在"),
    REFUND_NO_OPERATION_RIGHT(20510, "没有操作权限"),
    REFUND_PASS_FAILED(20511, "退款通过操作失败,请刷新重试"),
    REFUND_STATUS_NOT_REFUNDING(20512, "该退款申请非申请中状态"),
    REFUND_REJECT_REASON_IS_BLANK(20513, "退款申请拒绝原因不能为空"),
    REFUND_REJECT_REASON_TOO_LONG(20514, "退款申请拒绝原因不得超过200字符"),
    REFUND_REJECT_FAILED(20515, "退款拒绝操作失败,请刷新再试"),
    REFUND_NO_ORDER_PAY_INFO(20516, "退款对应订单无支付信息"),
    REFUND_ALREADY_APPLY_REMITTANCE(20517, "该售后记录已申请打款"),
    REFUND_UPDATE_REMITTANCE_STATUS_FAIL(20518, "更新打款状态失败"),
    REFUND_ADD_FAIL(20519, "新增售后单失败，请稍后再试"),
    REFUND_IMG_CAN_NOT_BE_EMPTY(20520, "请上传售后凭证"),
    REFUND_NO_ORDER_PRODUCT(20521, "无此订单商品"),
    REFUND_NO_PRODUCT_IMAGE(20522, "无商品图像信息"),
    REFUND_NO_REASON(20523, "无可用的退款原因"),
    REFUND_CANNOT_PROMPT(20524, "无法催促"),
    REFUND_NO_RECORD(20524, "无退款记录"),
    REFUND_CANCEL_LIMIT(20525, "该退款状态不是申请中，不能撤销"),
    REFUND_MIN_PRICE_LIMIT(20526, "退款金额必须大于1分"),
    REFUND_MAX_PRICE_LIMIT(20527, "申请退款金额大于可退款最高价"),
    REFUND_NO_REPEAT(20528, "该商品有退款记录且不可再次申请退款"),
    REFUND_NO_ORDER_BATCH_NUMBER(20529, "不存在订单批次信息"),
    REFUND_EXIST_NOT_SAME_ORDER_PRODUCT(20530, "存在非同一订单的订单商品"),
    REFUND_UNSATISFIED_FULL_ORDER_TYPE(20531, "当前订单类型不支持整单退款"),
    REFUND_UPDATE_ADJUST_COUNT_FAIL(20532, "申请退款商品数量必须大于0，且不能大于购买数量"),
    REFUND_NO_ORDER(20533, "不存在订单信息"),
    REFUND_STOCK_ADJUST_FAIL(20533, "调整归还库存失败"),
    REFUND_CANCEL_COUNT_LIMIT(20534, "退款信息中存在不是退款中的状态，不能撤销"),


    // 店铺
    SHOP_NOT_EXIST(20701, "店铺不存在"),
    SHOP_NOT_AVAILABLE(20702, "店铺已停用"),
    SHOP_TYPE_WRONG(20703, "店铺类型错误"),
    SHOP_OFF_DISCOUNT_CAN_NOT_ON(20704, "店铺已停用，无法支付"),
    SHOP_NOT_BELONG_TO_CURRENT_PARTNER_COMPANY(20705, "店铺不属于当前登陆合伙人公司"),
    SHOP_PASSWORD_NOT_EQUALS_CONFIRM_PASSWORD(20706, "两次密码不一致"),
    PARTNER_NOT_BELONG_TO_CURRENT_PARTNER_COMPANY(20707, "合伙人不属于当前合伙人公司"),
    SHOP_ADD_USERNAME_IS_EXIST(20708, "用户名已存在"),
    SHOP_ADD_GS_ID_IS_EXIST(20709, "捕手账号ID已经存在"),
    SHOP_ADD_GS_ID_IS_NOT_VALID(20710, "无效的捕手账号ID"),
    SHOP_ADD_SEARCH_GS_ID_EXCEPTION(20711, "查询捕手账号出错"),
    SHOP_ADD_VALID_RATE(20712, "非法的优惠比例"),
    SHOP_DELIVERY_IS_OFF(20713, "外卖已经关闭"),
    SHOP_ADD_NAME_EXIST(20714, "店铺名称已经存在"),
    SHOP_ADD_TIME_CONFLICT(20715, "营业时间冲突"),
    SHOP_LIST_VALID_ID(20716, "错误的ID"),
    SHOP_LIST_VALID_GS_ID(20717, "错误的捕手账号ID"),
    SHOP_NOT_BELONG_TO_CURRENT_GS_ID(20718, "店铺不属于当前用户"),
    SHOP_NOT_EXIST_TO_THIS_CITY(20719, "该城市没有开通本地生活服务"),
    SHOP_DELIVERY_NOTICE_TOO_LONG(20720, "配送公告不能超过20个字符"),
    SHOP_NEXT_DAY_DELIVERY_TIME_OUT_OF_RANGE(20721,"次日达配置时间不在有效范围(9-20点有效)"),

    // 商品
    PRODUCT_NOT_EXIST(20801, "商品不存在"),
    PRODUCT_NOT_AVAILABLE(20802, "商品已失效"),
    PRODUCT_GROUP_UPDATE_ILLEGAL(20803, "非法的商品分组更新"),
    PRODUCT_NOT_BELONG_CURRENT_SHOP(20804, "商品不属于当前店铺"),
    PRODUCT_CAN_NOT_ADD_SHOP_OFF(20805, "店铺停用，不能新增商品"),
    PRODUCT_CAN_NOT_SALE_SHOP_OFF(20806, "店铺停用，不能上架商品"),
    PRODUCT_CAN_NOT_SALE_NO_DELIVERY_AREA(20807, "未设置配送范围，不允许上架"),
    PRODUCT_CAN_NOT_SALE_GROUP_NOT_SALE(20808, "商品分组停用，不允许上架"),
    PRODUCT_ADD_PRODUCT_GROUP_VALID(20809, "非法的商品分组"),
    PRODUCT_GROUP_NAME_TOO_LONG(20810, "商品分组名称最多8个汉字"),
    PRODUCT_GROUP_NAME_EXIST(20811, "商品分组名称已经存在"),
    PRODUCT_CAN_NOT_SALE_WITH_OUT_AVAILABLE_SKU(20812, "不存在可用规格，不允许上架"),
    SKU_NOT_EXIST_IN_PRODUCT_CENTER(208013, "SKU在商品中心不存在"),
    SKU_NOT_NEED_UPDATE(208014, "无需要修改库存SKU"),

    // 合伙人
    COMMISSION_RATE_ERROR(20901, "分佣比例设置必须相加等于100"),
    DISTRICT_ERROR(20902, "地区错误，一个地区下只能有一家合伙人公司"),
    SWITCH_STATUS_ERROR(20903, "该公司下有关联的商铺，无法停用该公司"),
    EXIST_GS_ERROR(20904, "该捕手账号已经被其他用户关联"),
    PARTNER_IS_NOT_VIP_ERROR(20905, "合伙人绑定的捕手账号必须是捕手会员"),
    PARTNER_COMPANY_NOT_EXIST(20906, "该合伙人公司不存在"),
    PARTNER_LIST_EMPTY_EXIST(20907, "合伙人列表不能为空"),
    PARTNER_DUPLICATE_NAME_ERROR(20908, "待添加的合伙人列表有重复的用户名，请核对后修改"),
    PARTNER_DUPLICATE_GS_ID_ERROR(20909, "待添加的合伙人列表有重复的捕手id，请核对后修改"),
    PARTNER_GS_ID_NOT_EXIST(20910, "捕手id不存在，请核对后修改"),
    COMMISSION_RATE_ZERO_ERROR(20911, "分佣比例不能为0"),
    PARTNER_USERNAME_UPDATE_ERROR(20912, "用户名无法修改"),
    PARTNER_PASSWORD_PATTERN_ERROR(20913, "密码格式错误，需为6-12位字母或数字，区分大小写"),
    PARTNER_UNAVAILABLE_ERROR(20914, "账户已失效，登录失败"),
    EMPTY_PARTNER_ERROR(20915, "启用失败，请完善合伙人信息")


    ;


    private int code;
    private String message;

    /**
     * app需要的状态
     */
    private int appResultStatus;

    ErrorEnum(int code, String message) {
        this(code, message, 0);
    }

    ErrorEnum(int code, String message, int appResultStatus) {
        this.code = code;
        this.message = message;
        this.appResultStatus = appResultStatus;
    }

}
