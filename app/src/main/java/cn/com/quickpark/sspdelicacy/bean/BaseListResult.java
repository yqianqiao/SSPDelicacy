package cn.com.quickpark.sspdelicacy.bean;

import java.util.List;

/**
 * Created by y on 2018/7/16.
 */

public class BaseListResult<T> {
    private int ret;

    private String msg;

    private long timestamp;

    private String token;

    private List<T> result;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
