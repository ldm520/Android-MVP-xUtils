package com.ldm.mvp.presenter;

/**
 * description：
 * 作者：ldm
 * 时间：20162016/9/20 10:13
 * 邮箱：1786911211@qq.com
 */
public interface ILoginOperator {
    void login(String userName, String password, IHttpResposeResultListener listener);//登录操作
}
