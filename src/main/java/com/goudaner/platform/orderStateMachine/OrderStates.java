package com.goudaner.platform.orderStateMachine;

public enum OrderStates {
    UNPAID(1,"待支付"),                 // 待支付
    WAITING_DELIVERY(2,"待发货"),                 // 待支付
    WAITING_FOR_RECEIVE(3,"待收货"),    // 待收货
    RECEIVE(4,"签收"),                  // 结束
    NOT_RECEIVE(5,"拒收"),                   // 结束
    CHOICE(501,"选择");
    private Integer code;
    private String msg;

    OrderStates(Integer code, String msg) {
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
