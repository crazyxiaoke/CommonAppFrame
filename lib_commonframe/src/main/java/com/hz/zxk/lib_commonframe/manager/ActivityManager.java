package com.hz.zxk.lib_commonframe.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * @author zhengxiaoke
 * @date 2019-06-06 22:03
 * Actiivty统一管理
 */
public class ActivityManager {

    private static volatile ActivityManager mInstance;

    private Stack<Activity> activities;

    private ActivityManager() {
        activities = new Stack<>();
    }

    public static ActivityManager getInstance() {
        if (mInstance == null) {
            synchronized (ActivityManager.class) {
                if (mInstance == null) {
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 添加统一管理
     *
     * @param activity
     */
    public void attach(Activity activity) {
        activities.add(activity);
    }

    /**
     * 移除/解绑
     *
     * @param detachActivity
     */
    public void detach(Activity detachActivity) {
        int size = activities.size();
        for (int i = 0; i < size; i++) {
            Activity activity = activities.get(i);
            if (activity == detachActivity) {
                activities.remove(i);
                i--;
                size--;
            }
        }
    }

    public void finish(Activity finishActivity) {
        int size = activities.size();
        for (int i = 0; i < size; i++) {
            Activity activity = activities.get(i);
            if (activity == finishActivity) {
                activities.remove(i);
                activity.finish();
                i--;
                size--;
            }
        }
    }

    public void finish(Class<? extends Activity> clazz) {
        int size = activities.size();
        for (int i = 0; i < size; i++) {
            Activity activity = activities.get(i);
            if (activity.getClass().getCanonicalName().equals(clazz.getCanonicalName())) {
                activities.remove(i);
                activity.finish();
                i--;
                size--;
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApplication(){
        int size=activities.size();
        for (int i = 0; i < size; i++) {
            Activity activity=activities.get(i);
            activities.remove(i);
            activity.finish();
            i--;
            size--;
        }
    }

    /**
     * 获取当前的Activity
     * @return
     */
    public Activity getCurrentActivity(){
        return activities.lastElement();
    }


}
