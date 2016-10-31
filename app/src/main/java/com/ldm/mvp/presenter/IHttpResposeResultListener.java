package com.ldm.mvp.presenter;

/**
 * description：通用网络请求处理接口
 * 作者：ldm
 * 时间：20162016/9/20 10:11
 * 邮箱：1786911211@qq.com
 */
public interface IHttpResposeResultListener {
    void onResSucess(String string);//网络请求成功，返回是json格式字符串

    void onResFail(String result);//网络请求失败，错误信息
}
