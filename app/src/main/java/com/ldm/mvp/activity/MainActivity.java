package com.ldm.mvp.activity;

import android.os.Bundle;

import com.ldm.mvp.R;
import com.ldm.mvp.base.BaseAct;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * @param
 * @author ldm
 * @description 通过Xutil的注解绑定功能，省下了findViewById()等很多代码
 * @time 2016/10/31 9:16
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseAct {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
