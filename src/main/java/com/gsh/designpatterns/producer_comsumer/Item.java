package com.gsh.designpatterns.producer_comsumer;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/19 14:35
 * @Description:
 */
public class Item {
    private String userId;

    private String picuterId;

    private String path;

    public Item(String userId, String picuterId, String path) {
        this.userId = userId;
        this.picuterId = picuterId;
        this.path = path;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPicuterId() {
        return picuterId;
    }

    public void setPicuterId(String picuterId) {
        this.picuterId = picuterId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
