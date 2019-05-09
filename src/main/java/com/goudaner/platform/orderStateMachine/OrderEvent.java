package com.goudaner.platform.orderStateMachine;

public enum OrderEvent {
    CREATE(0,"CREATE","创建订单"),        // 支付
    PAY(1,"PAY","支付"),        // 支付
    DELIVERY(2,"DELIVERY","发货"),
    RECEIVE(3,"RECEIVE","收货"),
    NOT_RECEIVE(4,"NOT_RECEIVE","拒收货");

    private Integer code;
    private String msg;
    private String value;

    OrderEvent(Integer code, String msg,String value) {
        this.code = code;
        this.msg = msg;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getDesc(Integer value) {
        OrderEvent[] orderEvents = values();
        for (OrderEvent orderEventsEnum : orderEvents) {
            if (orderEventsEnum.getCode().equals(value)) {
                return orderEventsEnum.getMsg();
            }
        }
        return null;
    }
}
