package com.ldm.mvp.presenter;

import com.ldm.mvp.base.Constant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.LogUtil;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * description：
 * 作者：ldm
 * 时间：20162016/9/20 10:17
 * 邮箱：1786911211@qq.com
 */
public class LoginOperator implements ILoginOperator {
    @Override
    public void login(String userName, String password, final IHttpResposeResultListener listener) {
        //调用接口，登录处理
        JSONObject json = new JSONObject();
        try {
            //传入接口请求参数
            json.put("userName", userName);
            json.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //传入接口请求地址
        RequestParams params = new RequestParams(Constant.HTTP_LOGIN_URL);
        //请求添加头部信息，根据项目需要
//        params.addHeader("deviceId", SystemUtil.getPhoneIMEI(GlobalApp.getInstance()));
        //设置传入的参数为json格式
        params.setAsJsonContent(true);
        //传入参数内容
        params.setBodyContent(json.toString());
        //xUtils提供的网络请求
        x.http().request(HttpMethod.POST, params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("请求结果：" + result);
                //加载成功回调，返回获取到的数据
                if (null != listener) {
                    listener.onResSucess(result);
                }
            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("请求失败：" + ex.toString());
                if (null != listener) {
                    listener.onResFail(ex.toString());
                }
            }
        });
    }
}
