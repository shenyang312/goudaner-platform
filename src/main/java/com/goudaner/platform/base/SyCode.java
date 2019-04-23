package com.goudaner.platform.base;

/**
 * create by emmet
 * create at 11/09/2017
 * 系统返回枚举
 */
public class SyCode extends SyObject {

    public static final SyCode OK = new SyCode("00", "操作成功");
    public static final SyCode FAIL = new SyCode("09", "失败");
    public static final SyCode ERR = new SyCode("10", "系统异常");
    public static final SyCode ERR_PARAM = new SyCode("11", "参数异常");
    public static final SyCode ERR_SIGN = new SyCode("12", "系统验签异常");
    public static final SyCode ERR_DB = new SyCode("13", "数据库异常");
    public static final SyCode TOKEN_EXPIRED = new SyCode("14", "系统token失效");
    public static final SyCode TIME_OUT = new SyCode("99", "系统请求超时");
    public static final SyCode ORDER_NONE = new SyCode("15","无此订单数据");
    public static final SyCode ORDER_ERROR = new SyCode("16","订单异常");
    public static final SyCode BACKAMT_ERROR = new SyCode("17","退款金额不对");
    public static final SyCode TRANSAMT_OVERRUN = new SyCode("18","催款金额超标");
    public static final SyCode ORDER_STATUS = new SyCode("19","订单状态异常");
    public static final SyCode TRANSFLOW_NONE = new SyCode("20","查不到流水");
    public static final SyCode TRANSFLOW_MORE = new SyCode("21","查到多条流水");
    public static final SyCode DETAIL_NONE= new SyCode("22","无详情单记录");
    public static final SyCode CARDARG2_ERROR = new SyCode("23","CVN2错误");
    public static final SyCode CARDARG3_ERROR = new SyCode("24","有效期错误");
    public static final SyCode CANCEL_ERROR = new SyCode("25","只能取消执行中或是待执行的订单");
    public static final SyCode CUSTOMER_NONE = new SyCode("26","找不到对应的客户号");
    public static final SyCode ACCOUNT_ERR = new SyCode("27","账户异常");
    public static final SyCode ORDER_REPAYING = new SyCode("28","订单正在执行,无法取消");
    public static final SyCode TOKEN_ERR = new SyCode("29","token验证失败");
    public static final SyCode VERSION_ERR = new SyCode("30","版本号格式错误");
    public static final SyCode EXPEN_EXCEED = new SyCode("31","结算费用不可大于上级渠道");
    public static final SyCode DATA_ERR = new SyCode("32","查询数据有异");
    private String code;
    private String desc;

    public SyCode() {}
    public SyCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean codeEquals(String respCode) {
        return this.code.equals(respCode);
    }
}
