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

    private List<Activity> activities;

    public static BaseApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
        activities=new ArrayList<>();
        //ARouter初始化
        ARouter.init(this);
    }

    /**
     * 添加Activity到列表中
     * @param activity
     */
    public void addActivity(Activity activity){
        this.activities.add(activity);
    }

    /**
     * 把Activity从列表中移除
     * @param activity
     */
    public void removeActivity(Activity activity){
        this.activities.remove(activity);
    }

    public void finishAllActivity(){
        if(activities!=null){
            for (Activity activity : activities) {
                activity.finish();
            }
        }
    }


}
