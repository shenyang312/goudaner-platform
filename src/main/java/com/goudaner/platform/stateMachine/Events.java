package com.goudaner.platform.stateMachine;

public enum Events {
    PAY(1,"支付"),        // 支付
    RECEIVE(2,"收货");     // 收货

    private Integer code;
    private String msg;

    Events(Integer code, String msg) {
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
