package com.hz.zxk.lib_commonframe.widget.navigationbar;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

/**
 * @author zhengxiaoke
 * @date 2019-06-07 22:48
 */
public class NavigationBar extends AbsNavigationBar{

    protected NavigationBar(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbsNavigationBar.Builder<Builder>{

        public Builder(Context context, @LayoutRes int layoutId, ViewGroup parent) {
            super(context, layoutId, parent);
        }

        @Override
        public NavigationBar create() {
            return new NavigationBar(this);
        }
    }
}
