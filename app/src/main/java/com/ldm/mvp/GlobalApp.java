package com.ldm.mvp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.ldm.mvp.model.UserModel;
import com.ldm.mvp.xutils.CrashHandler;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * description：项目自定义Application：常用来初始化全局配置，如地图开发等第三方依赖
 * 作者：ldm
 * 时间：20162016/10/27 16:41
 * 邮箱：1786911211@qq.com
 */
public class GlobalApp extends Application {
    private static GlobalApp instance;
    private static final String LOCAL_PKG_NAME = "com.ldm.mvp";
    private UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public static GlobalApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LOCAL_PKG_NAME.equals(getCurrProcName())) {
            activitys = new ArrayList<>();
            CrashHandler.create(this);
            instance = this;
            //在自定义的全局Application中初始化XUtils3.0
            x.Ext.init(this);
        }
    }

    /**
     * @param
     * @description 获取当前应用的进程名, 默认是包名
     * @author ldm
     * @time 2016/10/27 16:59
     */
    private String getCurrProcName() {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    private List<Activity> activitys = null;

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        } else {
            assert activitys != null;
            activitys.add(activity);
        }

    }

    /**
     * @param
     * @description 退出程序 ：遍历所有Activity并finish
     * @author ldm
     * @time 2016/10/27 17:00
     */
    public void exit() {
        if (activitys != null && activitys.size() > 0) {
            for (Activity activity : activitys) {
                activity.finish();
            }
        }
        System.exit(0);
    }
}
