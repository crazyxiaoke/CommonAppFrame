package com.hz.zxk.lib_commonframe.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.AnimRes;
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
    private FragmentManager fm;
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeCreate();
        setContentView(getLayoutId());
        init();
        initView();
        mPresenter=initPresenter();
        if(mPresenter!=null){
            mPresenter.attchView((V) this);
        }
    }

    private void init(){
        mThisActivity=this;
        mContext=this;
        fm=getSupportFragmentManager();
    }

    /**
     * 放一些需要在setContentView之前执行的代码
     */
    protected void beforeCreate(){

    }

    /**
     * 添加fragment
     * @param resId
     * @param fragment
     */
    protected void addFragment(int resId,Fragment fragment){
        this.addFragment(resId,fragment,0,0);
    }

    /**
     * 添加fragment,自定义转场动画
     * @param resId
     * @param fragment
     * @param enterAnim 进入动画
     * @param exitAnim 退出动画
     */
    protected void addFragment(int resId, Fragment fragment,
                               @AnimRes int enterAnim, @AnimRes int exitAnim){
        FragmentTransaction ft=fm.beginTransaction();
        if(enterAnim!=0&&exitAnim!=0) {
            ft.setCustomAnimations(enterAnim, exitAnim);
        }
        ft.add(resId,fragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 更换fragment
     * @param resId
     * @param fragment
     */
    protected void replaceFragment(int resId,Fragment fragment){
        this.replaceFragment(resId,fragment,0,0);
    }

    /**
     * 更换fragment,自定义转场动画
     * @param resId
     * @param fragment
     * @param enterAnim 进入动画
     * @param exitAnim 退出动画
     */
    protected void replaceFragment(int resId,Fragment fragment,
                                   @AnimRes int enterAnim,@AnimRes int exitAnim){
        FragmentTransaction ft=fm.beginTransaction();
        if(enterAnim!=0&&exitAnim!=0){
            ft.setCustomAnimations(enterAnim,exitAnim);
        }
        ft.replace(resId,fragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 移除fragment
     * @param resId
     * @param fragment
     */
    protected void removeFragment(int resId,Fragment fragment){
        this.removeFragment(resId,fragment);
    }

    /**
     * 移除fragment，自定义动画
     * @param resId
     * @param fragment
     * @param enterAnim
     * @param exitAnim
     */
    protected void removeFragment(int resId,Fragment fragment,
                                  @AnimRes int enterAnim,@AnimRes int exitAnim){
        FragmentTransaction ft=fm.beginTransaction();
        if(enterAnim!=0&&exitAnim!=0){
            ft.setCustomAnimations(enterAnim,exitAnim);
        }
        ft.remove(fragment);
        ft.commitAllowingStateLoss();
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
        if(mPresenter!=null) {
            mPresenter.clearDisposable();
            mPresenter.destoryView();
        }
    }

    protected abstract P initPresenter();  //初始化presenter

    protected abstract int getLayoutId(); //获取layoutId

    protected abstract void initView(); //初始化视图

}
