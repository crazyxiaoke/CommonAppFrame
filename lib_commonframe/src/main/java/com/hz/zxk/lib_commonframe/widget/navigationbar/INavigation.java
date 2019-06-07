package com.hz.zxk.lib_commonframe.widget.navigationbar;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhengxiaoke
 * @date 2019-06-07 22:35
 */
public interface INavigation {
    /**
     * 创建NavigationBar
     */
    void createNavigationBar();

    /**
     * 添加NavigationBar参数
     */
    void attachNavigationParam();

    /**
     * 添加到父容器中
     * @param navigationBar
     * @param parent
     */
    void attachParent(View navigationBar, ViewGroup parent);

}
