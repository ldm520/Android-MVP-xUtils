package com.ldm.mvp.model;

import java.io.Serializable;

/**
 * @param
 * @author ldm
 * @description 响应数据解析
 * @time 2016/10/28 17:54
 */
public class HttpResModel<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int errCode;// 结果状态，约定返回0表示接口请求成功
    private String errMsg;// 响应请求结果说明（如登录时提示密码错误）
    private int total;// 列表数据数量
    private T result;// 返回的数据（json格式数据）

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HttpResponBean [errCode=" + errCode + ", errMsg=" + errMsg + ", total=" + total + ", result="
                + result + "]";
    }

}
