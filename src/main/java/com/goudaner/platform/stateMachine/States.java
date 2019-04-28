package com.goudaner.platform.stateMachine;

public enum States {
    UNPAID(1,"待支付"),                 // 待支付
    WAITING_FOR_RECEIVE(2,"待收货"),    // 待收货
    DONE(3,"结束");                    // 结束
    private Integer code;
    private String msg;

    States(Integer code, String msg) {
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
