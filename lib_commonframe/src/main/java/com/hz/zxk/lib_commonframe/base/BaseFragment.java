package com.hz.zxk.lib_commonframe.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhengxiaoke
 * @date 2019/3/19 3:56 PM
 */
public abstract class BaseFragment<P extends BasePresenter<V>,V extends IBaseView> extends Fragment
    implements IBaseView{
    private Context mContext;
    private Activity mActivity;
    private FragmentManager fm;

    P mPresenter;

    public BaseFragment(){
        mPresenter=initPresenter();
        if(mPresenter!=null){
            mPresenter.attchView((V) this);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getLayoutId(),container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fm=getChildFragmentManager();
        mContext=getContext();
        mActivity=getActivity();
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
     * 显示fragment
     * @param fragment
     */
    protected void showFragment(Fragment fragment){
        this.showFragment(fragment,0,0);
    }

    /**
     * 显示fragment,自定义转场动画
     * @param fragment
     * @param enterAnim 进入动画
     * @param exitAnim  退出动画
     */
    protected void showFragment(Fragment fragment,@AnimRes int enterAnim,@AnimRes int exitAnim){
        FragmentTransaction ft=fm.beginTransaction();
        if(enterAnim!=0&&exitAnim!=0){
            ft.setCustomAnimations(enterAnim,exitAnim);
        }
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 隐藏fragment
     * @param fragment
     */
    protected void hideFragment(Fragment fragment){
        this.hideFragment(fragment,0,0);
    }

    /**
     * 隐藏fragment,自定义转场动画
     * @param fragment
     * @param enterAnim 进入动画
     * @param exitAnim  退出动画
     */
    protected void hideFragment(Fragment fragment,@AnimRes int enterAnim,@AnimRes int exitAnim){
        FragmentTransaction ft=fm.beginTransaction();
        if(enterAnim!=0&&exitAnim!=0){
            ft.setCustomAnimations(enterAnim,exitAnim);
        }
        ft.hide(fragment);
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
        //显示加载框
    }

    @Override
    public void hideLoadingDialog() {
        //隐藏加载框
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.clearDisposable();
            mPresenter.destoryView();
            mPresenter=null;
        }
    }

    protected abstract P initPresenter(); //初始化presenter

    protected abstract int getLayoutId(); //获取布局id
}
