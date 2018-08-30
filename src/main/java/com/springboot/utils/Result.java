package com.springboot.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.core.exception.CrmException;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private String code;

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMsg;

    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static Result<?> success() {
        return Result.success(null);
    }

    public static Result<?> failure(ResultCodeEnum resultCodeEnum) {
        return Result.failure(resultCodeEnum.getCode(), resultCodeEnum.getMsg());
    }

    public static Result<?> failure(ResultCodeEnum resultCodeEnum, String errorMsg) {
        return Result.failure(resultCodeEnum.getCode(), resultCodeEnum.getMsg(), errorMsg);
    }

    public static Result<?> failure(String code, String msg) {
        return Result.failure(code, msg, null);
    }

    public static Result<?> failure(CrmException e) {
        return Result.failure(e, e.getCode(), e.getMsg(), e.getMessage());
    }

    public static Result<?> failure(Throwable e, ResultCodeEnum resultCodeEnum) {
        return failure(e, resultCodeEnum.getCode(), resultCodeEnum.getMsg(), e.getMessage());
    }

    public static Result<?> failure(String code, String msg, String errorMsg) {
        return failure(null, code, msg, errorMsg);
    }

    public static <T> Result<?> failure(T obj, String code, String msg) {
        return failure(obj, code, msg, null);
    }

    public static <T> Result<?> success(T obj) {
        Result<Object> result = new Result<Object>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.OK.getCode());
        result.setMsg(ResultCodeEnum.OK.getMsg());
        if (obj == null) {
            // 若返回数据为null 统一返回给前端{}
            result.setData(new HashMap<>(1));
        } else {
            result.setData(obj);
        }
        return result;
    }

    public static <T> Result<T> failure(T obj, String code, String msg, String errorMsg) {
        Result<T> result = new Result<T>();
        result.setData(obj);
        result.setCode(code);
        result.setSuccess(false);
        result.setMsg(msg);
        result.setErrorMsg(errorMsg);
        return result;
    }

    public static Result<?> failure(BindingResult br) {
        if (null != br && br.hasErrors()) {
            Map<String, String> map = new HashMap<String, String>(16);
            List<FieldError> list = br.getFieldErrors();
            Iterator<FieldError> var3 = list.iterator();
            StringBuilder s = new StringBuilder();
            while (var3.hasNext()) {
                FieldError error = (FieldError) var3.next();
                map.put(error.getField(), error.getDefaultMessage());
                s.append(error.getDefaultMessage()).append("，");
            }
            if (s.length() > 0) {
                s.deleteCharAt(s.length() - 1);
            }
            return failure(map, ResultCodeEnum.PARAM_ERROR.getCode(), s.toString());
        } else {
            return failure(ResultCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

}
