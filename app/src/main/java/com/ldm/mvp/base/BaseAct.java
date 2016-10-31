package com.ldm.mvp.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;

import com.ldm.mvp.view.LoadingDialog;

/**
 * description：项目中Activity基类
 * 作者：ldm
 * 时间：20162016/10/27 17:01
 * 邮箱：1786911211@qq.com
 */
public class BaseAct extends Activity {
    protected LoadingDialog mLoadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mLoadingDialog = new LoadingDialog(this);
    }

    protected void showLoadingDialog() {
        if (null != mLoadingDialog) {
            mLoadingDialog.show();
        }
    }

    protected void closeLoadingDialog() {
        if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * @param
     * @description 页面跳转
     * @author ldm
     * @time 2016/10/27 17:32
     */
    protected void showActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        activity.startActivity(intent);
    }

    /**
     * @param
     * @description 页面跳转，带传数据
     * @author ldm
     * @time 2016/10/27 17:32
     */
    public void showActivity(Activity activity, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(activity, cls);
        activity.startActivity(intent);
    }

    /* 获取输入内容 */
    public String getInputStr(EditText et) {
        return et.getText().toString().trim();
    }
}
