package com.goudaner.platform.orderStateMachine;

public enum OrderStates {
    UNPAID(1,"UNPAID","待支付"),                 // 待支付
    WAITING_DELIVERY(2,"WAITING_DELIVERY","待发货"),                 // 待支付
    WAITING_FOR_RECEIVE(3,"WAITING_FOR_RECEIVE","待收货"),    // 待收货
    RECEIVE(4,"RECEIVE","签收"),                  // 结束
    NOT_RECEIVE(5,"NOT_RECEIVE","拒收"),                   // 结束
    CHOICE(501,"CHOICE","选择");
    private Integer code;
    private String msg;
    private String value;

    OrderStates(Integer code, String msg,String value) {
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
        OrderStates[] orderStates = values();
        for (OrderStates orderStatesEnum : orderStates) {
            if (orderStatesEnum.getCode().equals(value)) {
                return orderStatesEnum.getMsg();
            }
        }
        return null;
    }
}
