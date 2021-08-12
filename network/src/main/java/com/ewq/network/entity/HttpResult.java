package com.ewq.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description: 网络返回消息，最外层解析实体
 */
@SuppressWarnings("unused")
public class HttpResult<T> {
    /**
     * 错误码
     */
    @SerializedName(Params.RES_CODE)
    private String code;

    /**
     * 错误信息
     */
    @SerializedName(Params.CODEDESCRIPTION)
    private String msg;

    /** 错误信息 */
    // private String codeDescription;

    /**
     * 消息响应的主体
     */
    @SerializedName(Params.RES_DATA)
    private T data;

    private PageMo page;

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

    public PageMo getPage() {
        return page;
    }

    public void setPage(PageMo page) {
        this.page = page;
    }
}
