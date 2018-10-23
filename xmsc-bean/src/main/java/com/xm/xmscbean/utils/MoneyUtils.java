package com.xm.xmscbean.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiuGaoJian
 * @version 1.0 2018/4/19
 */
public class MoneyUtils {
    private static final BigDecimal HUNDRED = new BigDecimal(100);

    private static final BigDecimal TEN_THOUSAND = new BigDecimal(10000);

    private MoneyUtils() {
    }

    public static BigDecimal fenToYuan(Integer number) {
        return new BigDecimal(number).divide(HUNDRED, 2, BigDecimal.ROUND_HALF_UP);
    }

    public static String fenToYuanStr(Integer number) {
        return fenToYuan(number).toString();
    }

    public static BigDecimal fenToYuan(Long number) {
        return new BigDecimal(number).divide(HUNDRED, 2, BigDecimal.ROUND_HALF_UP);
    }

    public static String fenToYuanStr(Long number) {
        return fenToYuan(number).toString();
    }


    /**
     * 将金额分转化为元或万元，小数点后保留两位有效数字。
     *
     * @param number 金额分
     * @return
     */
    public static String fenToWanYuanStr(Long number) {
        if (number >= 1000000) {
            number = number / 10000;
            return rvZeroAndDot(fenToYuanStr(number)) + "万元";
        } else {
            return rvZeroAndDot(fenToYuanStr(number)) + "元";
        }
    }

    /**
     * 将金额分转化为元或万元，小数点后保留两位有效数字。
     *
     * @param number 金额分
     * @return
     */
    public static String fenToWanStr(Long number) {
        if (number >= 1000000) {
            number /= 10000;
            return rvZeroAndDot(fenToYuanStr(number)) + "万";
        } else {
            return rvZeroAndDot(fenToYuanStr(number));
        }
    }

    /**
     * 去掉小数点后面多余的0
     *
     * @param s
     * @return
     */
    public static String rvZeroAndDot(String s) {
        if (s.isEmpty()) {
            return null;
        }
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }


    /**
     * 分转化为元 去除后缀0
     *
     * @param number 分
     * @return 元
     */
    public static String fenToYuanStrWithoutDotZero(Long number) {
        return rvZeroAndDot(fenToYuanStr(number));
    }


    /**
     * 分转化为元 去除后缀0
     *
     * @param number 分
     * @return 元
     */
    public static String fenToYuanStrWithoutDotZero(Integer number) {
        if (number == null){
            return "";
        }
        return rvZeroAndDot(fenToYuanStr(number));
    }

    /**
     * 分转元
     *
     * @param number 分
     * @param rate   折扣 整数（8折 rate 为20）
     * @return
     */
    public static String fenToYuanStrWithRate(int number, int rate) {
        return new BigDecimal(number * rate).divide(TEN_THOUSAND, 2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static Integer yuanToFen(BigDecimal number) {
        return number.multiply(HUNDRED).intValue();
    }

    public static Integer yuanStrToFen(String number) {
        return yuanToFen(new BigDecimal(number));
    }

    /**
     * 优惠比例转换
     *
     * @param discountRate
     * @return
     */
    public static String discountRateToStr(Byte discountRate) {
        String rateStr = new BigDecimal(100 - discountRate).divide(BigDecimal.TEN, 1, BigDecimal.ROUND_HALF_UP).toString();
        return rateStr.replaceAll("0+?$", "").replaceAll("[.]$", "");
    }

    /**
     * 四舍五入
     *
     * @param cent
     * @return
     */
    public static int rounding(BigDecimal cent) {
        return cent.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 按照规定的比例分配成若干份。分配所剩的零头
     *
     * @param cent   待分配金额, 单位分
     * @param ratios 分配比例数组, 每一个比例代表相对于总数的相对数。
     * @return 货币对象数组，数组的长度与分配比例数组的长度相同。
     */
    public static int[] allocate(long cent, List<Integer> ratios) {
        if (cent == 0) {
            return new int[ratios.size()];
        }
        int total = 0;

        for (Integer ratio : ratios) {
            total += ratio;
        }

        if (total <= 0) {
            throw new IllegalArgumentException("总比例不能为小于0");
        }

        long remainder = cent;

        int[] results = new int[ratios.size()];
        for (int i = 0; i < results.length; i++) {
            int money = (int) (cent * ratios.get(i) / total);
            results[i] = money;
            remainder -= money;
        }
        for (int i = 0; i < remainder; i++) {
            results[i]++;
        }

        return results;
    }

    public static void main(String[] args) {
        System.out.println(rounding(new BigDecimal("0.36")));
        System.out.println(rounding(new BigDecimal("0.46")));
        System.out.println(rounding(new BigDecimal("0.56")));
        System.out.println(rounding(new BigDecimal("0.96")));
        System.out.println("--------------------------------");

        int[] result = allocate(3, Arrays.asList(50,0,30,20));

        for (int i : result) {
            System.out.print(i + "\t");
        }

        System.out.println(getCost(9900,60));
        System.out.println(getDiscountedPrice(888,10));
    }

    /**
     * 获取成本价 单位分
     * @param price 原价
     * @param costRate 成本比例
     * @return
     */
    public static int getCost(int price, int costRate) {
        return price * costRate /100;
    }

    /**
     * 获取折后价 单位分
     * @param price 原价
     * @param discountRate 优惠比例
     * @return
     */
    public static int getDiscountedPrice(int price, int discountRate) {
        return price * (100 - discountRate) / 100;
    }
}
