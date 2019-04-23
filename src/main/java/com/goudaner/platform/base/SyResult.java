package com.goudaner.platform.base;

/**
 * create by emmet
 * create at 11/09/2017
 * 通用返回对象
 */
public class SyResult<T> extends SyObject {
    private String respCode;//response code, use EmmetCode
    private String respDesc;//response desc
    private T respData;//response java object

    public SyResult() {}

    public SyResult(SyCode zmCode) {
        this(zmCode, null);
    }

    public SyResult(T respData) {
        this(SyCode.OK, respData);
    }

    public SyResult(SyCode zmCode, T respData) {
        this(zmCode.getCode(), zmCode.getDesc(), respData);
    }

    public SyResult(String respCode, String respDesc) {
        this(respCode, respDesc, null);
    }

    public SyResult(String respCode, String respDesc, T respData) {
        this.respCode = respCode;
        this.respDesc = respDesc;
        this.respData = respData;
    }

    public boolean isSuccess() {
        return SyCode.OK.getCode().equals(this.getRespCode());
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public T getRespData() {
        return respData;
    }

    public void setRespData(T respData) {
        this.respData = respData;
    }
}
