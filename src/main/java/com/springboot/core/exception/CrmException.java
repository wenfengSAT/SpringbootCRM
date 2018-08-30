package com.springboot.core.exception;

import com.springboot.utils.ResultCodeEnum;

public class CrmException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;

    private String msg;

    public CrmException(String msg) {
        super(msg);
    }

    public CrmException(ResultCodeEnum resultCodeEnum) {
        this(resultCodeEnum.getCode(), resultCodeEnum.getMsg());
    }

    public CrmException(ResultCodeEnum resultCodeEnum, String msg) {
        super(msg);
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getCode();
    }

    public CrmException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
