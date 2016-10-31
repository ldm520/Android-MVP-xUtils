package com.ldm.mvp.model;

import java.io.Serializable;

/**
 * description：MVP模式中的Model模式，主要是业务逻辑处理或实体类
 * 作者：ldm
 * 时间：20162016/9/20 10:08
 * 邮箱：1786911211@qq.com
 */
public class UserModel implements Serializable {
    private String userName;
    private String userId;
    private String nickName;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
