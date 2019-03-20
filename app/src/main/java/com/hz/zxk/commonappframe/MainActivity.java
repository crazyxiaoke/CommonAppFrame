package com.hz.zxk.commonappframe;

import android.view.View;


import com.hz.zxk.lib_commonframe.arouter.ARouterHelper;
import com.hz.zxk.lib_commonframe.base.BaseActivity;
import com.hz.zxk.lib_commonframe.base.BasePresenter;


public class MainActivity extends BaseActivity {

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouterHelper.getInstance().build("/page/main/second")
                        .withString("name","晓克")
                        .withAnimation(R.anim.push_left_in,R.anim.push_left_out)
                        .navigation();
            }
        });
    }
}
