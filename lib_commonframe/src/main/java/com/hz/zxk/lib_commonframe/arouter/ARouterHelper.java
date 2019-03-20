package com.hz.zxk.lib_commonframe.arouter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnimRes;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import java.io.Serializable;

/**
 * @author zhengxiaoke
 * @date 2019/3/20 2:56 PM
 */
public class ARouterHelper {
    private Context context;
    private Postcard postcard;
    private Bundle mBundle;
    private static ARouterHelper sInstance;

    private int mEnterAnim; //进入动画
    private int mExitAnim;  //退出动画

    private int defaultEnterAnim;
    private int defaultExitAnim;

    public ARouterHelper() {

    }

    public ARouterHelper(Postcard postcard) {
        this.postcard = postcard;
    }

    public static ARouterHelper getInstance() {
        if (sInstance == null) {
            synchronized (ARouterHelper.class) {
                if (sInstance == null) {
                    sInstance = new ARouterHelper();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        this.context = context;
    }

    public void initAnim(@AnimRes int enterAnim, @AnimRes int exitAnim) {
        defaultEnterAnim = enterAnim;
        defaultExitAnim = exitAnim;
    }

    public ARouterHelper build(String url) {
        postcard = ARouter.getInstance().build(url);
        mBundle = new Bundle();
        return this;
    }


    /**
     * 传递int类型参数
     *
     * @param key
     * @param value
     * @return
     */
    public ARouterHelper withInt(String key, int value) {
        mBundle.putInt(key, value);
        return this;
    }

    /**
     * 传递String类型参数
     *
     * @param key
     * @param value
     * @return
     */
    public ARouterHelper withString(String key, String value) {
        mBundle.putString(key, value);
        return this;
    }

    /**
     * 传递Boolean类型参数
     *
     * @param key
     * @param value
     * @return
     */
    public ARouterHelper withBoolean(String key, boolean value) {
        mBundle.putBoolean(key, value);
        return this;
    }

    /**
     * 传递Float参数类型
     *
     * @param key
     * @param value
     * @return
     */
    public ARouterHelper withFloat(String key, float value) {
        mBundle.putFloat(key, value);
        return this;
    }

    /**
     * 传递Double类型参数
     *
     * @param key
     * @param value
     * @return
     */
    public ARouterHelper withDouble(String key, float value) {
        mBundle.putDouble(key, value);
        return this;
    }

    /**
     * 传递Serializable类型参数
     *
     * @param key
     * @param value
     * @return
     */
    public ARouterHelper withSerializable(String key, Serializable value) {
        mBundle.putSerializable(key, value);
        return this;
    }

    /**
     * 传递Parcelable类型参数
     *
     * @param key
     * @param parcelable
     * @return
     */
    public ARouterHelper withParcelable(String key, Parcelable parcelable) {
        mBundle.putParcelable(key, parcelable);
        return this;
    }

    /**
     * 批量导入参数
     *
     * @param bundle
     * @return
     */
    public ARouterHelper withBundle(Bundle bundle) {
        mBundle.putAll(bundle);
        return this;
    }

    /**
     * 转场动画
     *
     * @param enterAnim
     * @param exitAnim
     * @return
     */
    public ARouterHelper withAnimation(@AnimRes int enterAnim, @AnimRes int exitAnim) {
        this.mEnterAnim = enterAnim;
        this.mExitAnim = exitAnim;
        return this;
    }

    public void navigation() {
        build();
        this.postcard.navigation();
    }

    public void navigation(Context context) {
        build();
        this.postcard.navigation(context);
    }

    public void navigation(Context context, NavigationCallback callback) {
        build();
        this.postcard.navigation(context, callback);
    }

    public void navigation(Activity context, int requestCode) {
        build();
        this.postcard.navigation(context, requestCode);
    }

    public void navigation(Activity context, int requestCode, NavigationCallback callback) {
        build();
        this.postcard.navigation(context, requestCode, callback);
    }


    private void build() {
        if (mBundle != null && mBundle.size() > 0) {
            postcard.with(mBundle);
        }
        Log.d("TAG", "defaultEnterAnim=" + defaultEnterAnim);
        Log.d("TAG", "defaultExitAnim=" + defaultExitAnim);
        if (mEnterAnim != 0 && mExitAnim != 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                        .makeCustomAnimation(context, mEnterAnim, mExitAnim);
                postcard.withOptionsCompat(activityOptionsCompat);
            } else {
                postcard.withTransition(mEnterAnim, mExitAnim);
            }
        } else if (defaultEnterAnim != 0 && defaultExitAnim != 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                        .makeCustomAnimation(context, defaultEnterAnim, defaultExitAnim);
                postcard.withOptionsCompat(activityOptionsCompat);
            } else {
                postcard.withTransition(defaultEnterAnim, defaultExitAnim);
            }
        }
    }

}
