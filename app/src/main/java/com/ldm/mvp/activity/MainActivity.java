package com.ldm.mvp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ldm.mvp.R;
import com.ldm.mvp.base.BaseAct;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import static android.content.ContentValues.TAG;

/**
 * @param
 * @author ldm
 * @description xUtils常用功能使用
 * 博客：http://blog.csdn.net/true100/article/details/51734217
 * @time 2016/10/31 9:16
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseAct {
    @ViewInject(R.id.xutils_get)
    private Button xutils_get;
    @ViewInject(R.id.xutils_post)
    private Button xutils_post;
    @ViewInject(R.id.xutils_upload)
    private Button xutils_upload;
    @ViewInject(R.id.xutils_image)
    private Button xutils_image;
    @ViewInject(R.id.xutils_iv)
    private ImageView xutils_iv;
    //假定的http请求路径
    private final String GET_URL = "";
    private final String POST_URL = "";
    private final String FILE_UPLOAD = "";
    private final String IMAGE_URL = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    //get方式请求
    @Event(type = View.OnClickListener.class, value = R.id.xutils_get)
    private void httpGet(View v) {
        //项目中加载框应该统一封装
        final ProgressDialog dia = new ProgressDialog(this);
        dia.setMessage("加载中....");
        dia.show();
        RequestParams params = new RequestParams(GET_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //加载成功回调，返回获取到的数据
                Log.i(TAG, "onSuccess: " + result);
            }

            @Override
            public void onFinished() {
                dia.dismiss();//加载完成
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //错误提示
                dia.dismiss();
            }
        });
    }

    //post方式请求
    @Event(type = View.OnClickListener.class, value = R.id.xutils_post)
    private void httpPost(View v) {
        final ProgressDialog dia = new ProgressDialog(this);
        dia.setMessage("加载中....");
        dia.show();
        RequestParams params = new RequestParams(POST_URL);
        params.addParameter("userName", "admin");//添加Post请求参数,如果是get请求，会直接添加到url后面
        params.addBodyParameter("passWord", "123456");
        //  params.addHeader("head", "给Head添加信息");//addHeader添加Head信息
        // params.toJSONString();//如果传递参数要求是json格式，可以通过这样来转化
        //如果已经把参数写成json格式了，可以设置如下设置
        //params.setAsJsonContent(true);//以json形式提交body参数
        //params.setBodyContent(json.toString());设置json格式的参数内容
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //加载成功回调，返回获取到的数据
                Log.i(TAG, "onSuccess: " + result);
            }

            @Override
            public void onFinished() {
                dia.dismiss();//加载完成
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //错误提示
                dia.dismiss();
            }
        });
    }

    //文件上传
    @Event(type = View.OnClickListener.class, value = R.id.xutils_upload)
    private void fileUpload(View v) {
        String upUrl = "/mnt/sdcard/pic/test.jpg";//指定要上传的文件
        final ProgressDialog dia = new ProgressDialog(this);
        dia.setMessage("加载中....");
        dia.show();
        //指定文件上传路径
        RequestParams params = new RequestParams(FILE_UPLOAD);
        //设置文件上传参数
        params.addBodyParameter("file", new File(upUrl));
        //上传文件
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //加载成功回调，返回获取到的数据
                Log.i(TAG, "onSuccess: " + result);
            }

            @Override
            public void onFinished() {
                dia.dismiss();//加载完成
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //取消上传
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //上传失败
                dia.dismiss();
            }
        });
    }

    //加载网络图片
    @Event(type = View.OnClickListener.class, value = R.id.xutils_image)
    private void httpImage(View v) {
        //直接调用 bind(ImageView view, String url)方法，传入对应控件及url即可
        x.image().bind(xutils_iv, IMAGE_URL);
    }
}
