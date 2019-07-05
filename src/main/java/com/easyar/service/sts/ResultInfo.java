package com.easyar.service.sts;

public class ResultInfo {
    private int statusCode;
    private long timestamp;
    private String msg;
    private SecurityToken result;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SecurityToken getResult() {
        return result;
    }

    public void setResult(SecurityToken result) {
        this.result = result;
    }
}
