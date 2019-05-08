package com.goudaner.platform.orderStateMachine;

public enum OrderEvent {
    CREATE(0,"创建订单"),        // 支付
    PAY(1,"支付"),        // 支付
    DELIVERY(2,"发货"),
    RECEIVE(3,"收货"),
    NOT_RECEIVE(4,"拒收货");

    private Integer code;
    private String msg;

    OrderEvent(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
