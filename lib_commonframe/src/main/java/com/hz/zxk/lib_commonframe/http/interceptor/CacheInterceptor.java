package com.hz.zxk.lib_commonframe.http.interceptor;

import android.content.Context;
import android.util.Log;

import com.hz.zxk.commonutils.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhengxiaoke
 * @date 2019/3/19 11:27 AM
 */
public class CacheInterceptor implements Interceptor {
    private static final String TAG="HTTP";
    private Context context;

    public CacheInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isNetworkConnection(context)) {
            Log.d(TAG,"没有网络，读取缓存");
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        Log.d(TAG, "网络状态=" + NetworkUtils.isNetworkConnection(context));
        if (NetworkUtils.isNetworkConnection(context)) {
            return response.newBuilder()
                    .addHeader("Cache-Control", "public, max-age=60")
                    .removeHeader("Pragma")
                    .build();
        } else {
            int maxTime = 4 * 24 * 60 * 60;
            return response.newBuilder()
                    //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxTime)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
