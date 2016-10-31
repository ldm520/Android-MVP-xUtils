package com.ldm.mvp.view;

import com.ldm.mvp.model.UserModel;

/**
 * description：MVP模式中VIEW模块接口，主要是处理View层的数据
 * 作者：ldm
 * 时间：20162016/9/20 10:07
 * 邮箱：1786911211@qq.com
 */
public interface ILoginViEW {
    void loginSucess(UserModel model);

    void showLoading();

    void hideLoading();

    void showFailedError(String msg);
}
