package com.hz.zxk.lib_commonframe.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author zhengxiaoke
 * @date 2019/3/19 3:43 PM
 */
public class BasePresenter<V extends IBaseView> {
    protected V mView;
    protected CompositeDisposable mDisposables;

    public BasePresenter() {
        mDisposables = new CompositeDisposable();
    }

    /**
     * 添加disposable
     *
     * @param disposable
     */
    protected void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }

    /**
     * 删除disposable
     *
     * @param disposable
     */
    protected void removeDisposable(Disposable disposable) {
        mDisposables.remove(disposable);
    }

    /**
     * 删除所有disposable
     */
    protected void clearDisposable() {
        if (mDisposables != null) {
            mDisposables.clear();
        }
    }

    /**
     * 绑定View
     *
     * @param mView
     */
    public void attchView(V mView) {
        this.mView = mView;
    }

    /**
     * 销毁View
     */
    public void destoryView() {
        this.mView = null;
    }
}
