package com.goudaner.platform.orderStateMachine;

public enum OrderEvent {
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
}
