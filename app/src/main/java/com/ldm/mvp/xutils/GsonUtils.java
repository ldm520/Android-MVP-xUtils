package com.ldm.mvp.xutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ldm.mvp.model.HttpResModel;

import java.lang.reflect.Type;

/**
 * description：通过Gson框架把服务器返回的json数据解析成对应地实体类
 * 作者：ldm
 * 时间：20162016/10/28 17:49
 * 邮箱：1786911211@qq.com
 */
public class GsonUtils {
    private static GsonUtils instance;
    private Gson mGson;

    GsonUtils() {
        mGson = new GsonBuilder().serializeNulls().create();
    }

    public static GsonUtils getInstance() {
        if (null == instance) {
            instance = new GsonUtils();
        }
        return instance;
    }

    public <T> HttpResModel<T> parseJsonResult(String json, Type typeOfT) {
        return mGson.fromJson(json, typeOfT);
    }

    /**
     * 对象转换成json字符串
     *
     * @param obj
     * @return
     */
    public String toJson(Object obj) {
        return mGson.toJson(obj);
    }

    /**
     * json字符串转成对象
     *
     * @param str
     * @param type
     * @return
     */
    public <T> T fromJson(String str, Type type) {
        return mGson.fromJson(str, type);
    }

    /**
     * json字符串转成对象
     *
     * @param str
     * @param type
     * @return
     */
    public <T> T fromJson(String str, Class<T> type) {
        return mGson.fromJson(str, type);
    }
}
