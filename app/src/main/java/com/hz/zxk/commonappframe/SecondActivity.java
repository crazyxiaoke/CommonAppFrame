package com.hz.zxk.commonappframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/page/main/second")
public class SecondActivity extends AppCompatActivity {

    @Autowired
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ARouter.getInstance().inject(this);

        ((TextView)findViewById(R.id.text)).setText("hello "+name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
