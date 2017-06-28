package com.fanggemasend.model;

import java.io.Serializable;

/**
 * @className: BaseModel
 * @classDescription: 网络请求响应基类
 * @author: leibing
 * @createTime: 2016/8/12
 */
public class BaseModel<T> implements Serializable {

    public String msg;
    public String resultdesc;
    public T spread;

    public boolean success() {
        if (msg.equals("OK") || msg.equals("ok"))
            return true;
        else
            return false;
    }
}