package com.ewq.network.exception;

import com.ewq.network.entity.HttpResult;

/**
 * Description: HTTP请求异常类
 */
@SuppressWarnings("unused")
public class ApiException extends RuntimeException {
    private HttpResult result;

    public ApiException(HttpResult result) {
        this.result = result;
    }

    public HttpResult getResult() {
        return result;
    }

    public void setResult(HttpResult result) {
        this.result = result;
    }
}
