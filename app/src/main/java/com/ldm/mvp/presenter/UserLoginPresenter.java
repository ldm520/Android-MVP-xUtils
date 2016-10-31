package com.ldm.mvp.presenter;

import com.google.gson.reflect.TypeToken;
import com.ldm.mvp.base.Constant;
import com.ldm.mvp.model.HttpResModel;
import com.ldm.mvp.model.UserModel;
import com.ldm.mvp.view.ILoginViEW;
import com.ldm.mvp.xutils.GsonUtils;

/**
 * description：Login中数据与View的桥梁Presenter
 * 作者：ldm
 * 时间：20162016/9/20 10:27
 * 邮箱：1786911211@qq.com
 */
public class UserLoginPresenter {
    private ILoginViEW view;
    private LoginOperator operator;

    public UserLoginPresenter(ILoginViEW view) {
        this.view = view;
        operator = new LoginOperator();
    }

    public void loginToServer(String userName, String pwd) {
        view.showLoading();
        operator.login(userName, pwd, new IHttpResposeResultListener() {
            @Override
            public void onResSucess(String result) {
                //解析数据
                HttpResModel<UserModel> bean = GsonUtils
                        .getInstance()
                        .parseJsonResult(
                                result,
                                new TypeToken<HttpResModel<UserModel>>() {
                                }.getType());
                if (bean.getErrCode() == Constant.HTTP_SUCESS) {//登录成功
                    view.loginSucess(bean.getResult());
                } else {//登录失败
                    view.showFailedError(bean.getErrMsg());
                }
                view.hideLoading();
            }

            @Override
            public void onResFail(String result) {
                view.showFailedError(result);
                view.hideLoading();
            }
        });
    }
}
