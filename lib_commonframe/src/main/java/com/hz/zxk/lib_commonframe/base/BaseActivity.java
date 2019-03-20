package com.hz.zxk.lib_commonframe.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @author zhengxiaoke
 * @date 2019/3/18 5:28 PM
 */
public abstract class BaseActivity<P extends BasePresenter<V>,V extends IBaseView> extends FragmentActivity
    implements IBaseView{
    private Activity mThisActivity;
    private Context mContext;

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mThisActivity=this;
        mContext=this;
        mPresenter=initPresenter();
        mPresenter.attchView((V) this);
    }

    protected abstract P initPresenter();  //初始化presenter

    protected abstract int getLayoutId(); //获取layoutId

    /**
     * 添加fragment
     * @param resId
     * @param fragment
     */
    protected void addFragment(int resId,Fragment fragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(resId,fragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 更换fragment
     * @param resId
     * @param fragment
     */
    protected void replaceFragment(int resId,Fragment fragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(resId,fragment);
        ft.commitAllowingStateLoss();
    }

    protected void removeFragment(int resId,Fragment fragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction fg=fm.beginTransaction();
        fg.remove(fragment);
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.clearDisposable();
        mPresenter.destoryView();
    }
}
