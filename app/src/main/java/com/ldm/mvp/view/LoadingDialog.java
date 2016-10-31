package com.ldm.mvp.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.ldm.mvp.R;

/**
 * @param
 * @author ldm
 * @description 自定义一个极简单的数据加载提示框
 * @time 2016/10/27 17:12
 */
public class LoadingDialog extends Dialog {
    private static final String[] LOADINGS = {"...", "..", "."};
    private TextView tvText;
    private TextView tvLoading;
    private static LoadingDialog instance;

    private Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what >= 0 && msg.what < LOADINGS.length) {
                //设置圆点效果
                tvLoading.setText(LOADINGS[msg.what]);
            }

            switch (msg.what) {
                case 0:
                    mHandler.sendEmptyMessageDelayed(1, 1000);
                    break;
                case 1:
                    mHandler.sendEmptyMessageDelayed(2, 1000);
                    break;
                case 2:
                    mHandler.sendEmptyMessageDelayed(0, 1000);
                    break;
            }
            return false;
        }
    });

    public static LoadingDialog getInstance() {
        return instance;
    }

    public LoadingDialog(Context context) {
        super(context, R.style.load_progress_style);

    }

    public LoadingDialog(Context context, long timeout) {
        super(context, R.style.load_progress_style);

    }

    protected LoadingDialog(Context context, int theme) {
        super(context, theme);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        instance = this;
        tvText = (TextView) findViewById(R.id.tv_text);
        tvLoading = (TextView) findViewById(R.id.tv_loading);
        //设置点击back键不消失
        setCancelable(true);
        //点击屏幕部分不消失
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        if (isShowing()) {
            return;
        }
        super.show();
        mHandler.sendEmptyMessage(0);
        tvText.setText(getContext().getString(R.string.loading));
    }

    @Override
    public void dismiss() {
        mHandler.removeMessages(0);
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        try {
            if (isShowing())
                super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
