package com.hz.zxk.commonappframe;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hz.zxk.lib_commonframe.arouter.ARouterHelper;
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
//        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
        ARouterHelper.getInstance().init(this);
        ARouterHelper.getInstance().initAnim(R.anim.push_right_in,R.anim.push_right_out);
    }
}
