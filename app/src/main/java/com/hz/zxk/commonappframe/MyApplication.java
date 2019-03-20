package com.hz.zxk.commonappframe;

import android.app.Application;

import com.hz.zxk.lib_commonframe.http.retrofit.RetrofitHelper;

/**
 * @author zhengxiaoke
 * @date 2019/3/18 10:36 PM
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitHelper.init(this,"http://192.168.1.45:17778/");
    }
}
