package com.ldm.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ldm.mvp.GlobalApp;
import com.ldm.mvp.R;
import com.ldm.mvp.base.BaseAct;
import com.ldm.mvp.model.UserModel;
import com.ldm.mvp.presenter.UserLoginPresenter;
import com.ldm.mvp.view.ILoginViEW;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @param
 * @author ldm
 * @description 登录页面：
 * 通过xUtils的注解绑定功能，省下了findViewById()等很多代码
 * @time 2016/10/31 9:16
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseAct implements ILoginViEW {
    @ViewInject(R.id.user_edt)
    private EditText user_edt;
    @ViewInject(R.id.pwd_edt)
    private EditText pwd_edt;
    @ViewInject(R.id.user_login)
    private Button user_login;
    private UserLoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        loginPresenter = new UserLoginPresenter(this);
    }

    //点击按钮进行登录操作
    @Event(type = View.OnClickListener.class, value = R.id.user_login)
    private void userLogin(View v) {
        loginPresenter.loginToServer(getInputStr(user_edt), getInputStr(pwd_edt));
    }

    @Override
    public void loginSucess(UserModel model) {
        //登录成功
        //第1步：把用户信息保存到本地
        GlobalApp.getInstance().setUserModel(model);
        //第2步：跳转到主界面中
        Intent in = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(in);
    }

    @Override
    public void showLoading() {
        showLoadingDialog();//显示加载对话框
    }

    @Override
    public void hideLoading() {
        closeLoadingDialog();//隐藏加载对话框
    }

    @Override
    public void showFailedError(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();//提示错误信息
    }
}
