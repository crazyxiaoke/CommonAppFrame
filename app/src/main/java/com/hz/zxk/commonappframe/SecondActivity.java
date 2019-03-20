package com.hz.zxk.commonappframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hz.zxk.lib_commonframe.base.BaseActivity;
import com.hz.zxk.lib_commonframe.base.BasePresenter;

@Route(path = "/page/main/second")
public class SecondActivity extends BaseActivity {

    @Autowired
    String name;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        ((TextView)findViewById(R.id.text)).setText("hello "+name);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
