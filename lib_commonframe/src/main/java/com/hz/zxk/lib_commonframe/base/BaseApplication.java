package com.hz.zxk.lib_commonframe.base;

import android.app.Activity;
import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengxiaoke
 * @date 2019/3/20 2:08 PM
 */
public class BaseApplication extends Application {


    public static BaseApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
        //ARouter初始化
        ARouter.init(this);
    }


}
