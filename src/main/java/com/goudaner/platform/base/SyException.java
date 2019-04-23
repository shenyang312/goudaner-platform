package com.goudaner.platform.base;


public class SyException extends RuntimeException {
    private String code;

    public SyException(SyCode syCode) {
        this(syCode.getCode(), syCode.getDesc());
    }

    public SyException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SyException(SyResult result){
        super(result.getRespDesc());
        this.code = result.getRespCode();
    }

    public SyException(String code, String message, Object... args) {
        super(String.format(message, args));
        this.code = code;
    }

    public SyException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
