package com.xm.xmsccommon;

import java.math.BigDecimal;

public class CommonConstant {
    private CommonConstant() {
    }

    /**
     * spring 环境
     */
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    /**
     * 日志追踪ID
     */
    public static final String TRACE_LOG_ID = "traceLogId";

    public static final String PARAMS = "params";
    public static final String SIGN = "sign";
    public static final String OS = "os";
    public static final String IS_APP = "isApp";
    public static final String FROM_WEB = "fromWeb";
    public static final String FORMAT = "format";
    public static final String VERSION = "version";
    public static final String CHANNEL = "channel";
    public static final String ACCOUNT_ID = "accountId";
    public static final String TOKEN = "token";

    /**
     * 数字100
     */
    public static final BigDecimal BIG_DECIMAL_100 = BigDecimal.valueOf(100);
    /**
     * 数字小数位
     */
    public static final int RATE_SCALE = 6;

    /**
     * 一周的天数
     */
    public static final int DAYS_OF_WEEK = 7;

    /**
     * 多边形最少3个点组成
     */
    public static final int MIN_NUM_POINT_FOR_POLYGON = 3;

    /**
     * 结算周期固定为T+4天
     */
    public static final short SETTLEMENT_CIRCLE = 4;

    /**
     * 返回给前端最多4个标签
     */
    public static final int MAX_TAG_NUM = 4;

    /**
     * 物流编号最长的长度
     */
    public static final int MAX_LOGISTICS_NUMBER_LENGTH = 20;

    /**
     * 手机号格式正则表达式
     */
    public static final String MOBILE_REGEX = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

    /**
     * 邮件地址格式正则表达式
     */
    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";

    /**
     * QQ号格式正则表达式
     */
    public static final String QQ_REGEX = "^[1-9][0-9]{5,}$";

    /**
     * 汉字 unicode范围
     */
    public static final String CHINESE_UNICODE_REGEX = "[\\u4E00-\\u9FA5]";

    /**
     * 大于0的最多保留两位小数的数字 正则表达式
     */
    public static final String POSITIVE_NUMBER_REGEX = "^[1-9]\\d*(\\.\\d{1,2})?|0.\\d?[1-9]$";

    /**
     * 商品分组最长的长度
     */
    public static final int PRODUCT_GROUP_NAME_MAX_LENGTH = 16;

    /**
     * Emoji表情插入数据库报错信息
     */
    public static final String EMOJI_DB_ERROR_MESSAGE = "Incorrect string value";

    /**
     * 店铺公告最长20个字符，中文算一个字符
     */
    public static final int SHOP_DELIVERY_NOTICE_MAX_LENGTH = 20;

    //
    public static final String PLATFORM_IDENTITY_CODE = "9";

    /**
     * 首页分类条件显示级别
     */
    public static final byte CATEGORY_LEVEL = 1;
    /**
     * 商铺主图类型标识
     */
    public static final byte SHOP_MAJOR_IMG_TYPE = 1;

    public static final String REFUND_URL = "https://wwww.baidu.com";


    /**
     * 提现最低金额
     */
    public static final int WITHDRAW_LOWEST_AMOUNT = 100 * 100;
    /**
     * 商家H5首页和数据中心 近${count}订单数量 目前count=5
     */
    public static final int SHOP_H5_LAST_ORDER_OFFSET = 0;
    public static final int SHOP_H5_LAST_ORDER_COUNT = 5;

    public static final int ADDRESS_FULL_NAME_MAX_LENGTH = 256;
    public static final int ADDRESS_ADDRESS_NAME_MAX_LENGTH = 256;
    public static final int ADDRESS_ADDRESS_DETAIL_MAX_LENGTH = 256;


    /**
     * 退款编号格式
     */
    public static final String REFUND_NUMBER_REGEX = "^TK[0-9]{15}$";
    /**
     * 订单编号格式
     */
    public static final String ORDER_NUMBER_REGEX = "^[0-9]{15}$";
    /**
     * 账号id格式
     */
    public static final String ACCOUNT_REGEX = "^[0-9]{1,10}$";

    /**
     * 最大excel导出数量
     */
    public static final Integer MAX_EXCEL_EXPORT_ROWS = 100000;

    /**
     * 店铺分享
     */
    public static final String SHOP_SHARE_DESC = "精彩本地生活尽在环球捕手";

    /**
     * 商品规格名称和值最长20个字符，中文算一个字符
     */
    public static final int PRODUCT_SKU_PROPERTY_MAX_LENGTH = 20;

    /**
     * 城市热卖标签最大个数
     */
    public static final int CITY_HOT_PRODUCT_TAG_MAX_SIZE = 2;
    /**
     * 次日达配送类型
     */
    public static final byte NEXT_DAY_DELIVERY_TYPE = 0;

    /**
     * 最大导出数
     */
    public static final int MAX_EXPORT_NUM = 100000;

    //通用数字
    public static final int COMMON_10 = 10;
    public static final int COMMON_20 = 20;
    public static final int COMMON_30 = 30;
    public static final int COMMON_100 = 100;
    public static final int COMMON_255 = 255;

//    /**
//     * 白名单ip
//     */
//    public static final List<String> WHITE_LIST_IPS = Arrays.asList(
//            "115.236.183.234",
//            "60.191.20.122",
//            "183.129.164.86",
//            "60.191.6.70",
//            "122.224.237.154",
//            "113.215.188.205",
//            "61.153.0.242"
//    );

    /**
     * 数据源-读库
     */
    public static final String DataSourceSlave = "slave";

    /**
     * 用户默认头像
     */
    public static final String ACCOUNT_DEFAULT_HEAD_IMAGE = "https://img.gegejia.com/68a109f55aac.png";

    /**
     * 平台使用字符编码
     */
    public static final int IS_SYSTEM_MAINTENANCE = 1;

    /**
     * 平台使用字符编码
     */
    public static final String CHARACTER_ENCODING = "UTF-8";
    public static final String EXCEPTION_TIP = "服务器开小差了，请稍后再试";

    /**
     * 中国大陆区号
     */
    public static final int ADDRESS_CODE = 86;
}
