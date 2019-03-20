package com.hz.zxk.commonappframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;


import com.hz.zxk.lib_commonframe.http.ApiService;
import com.hz.zxk.lib_commonframe.http.retrofit.RetrofitHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Cookie;
import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("screenWidth","1080");
        queryMap.put("system","android");
        queryMap.put("regionId","3301");
        queryMap.put("latitude","0");
        queryMap.put("longitude","0");
        queryMap.put("screenHeight","1794");
        queryMap.put("version","4.1.1");
        queryMap.put("ttid","okxueche");
        queryMap.put("device","Android");
        queryMap.put("deviceId","358240051111110");
        RetrofitHelper.getInstance()
                .get("getClassInfoNew", queryMap, new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.d("HTTP","ResponseBody="+responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
