package com.hz.zxk.lib_commonframe.http.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhengxiaoke
 * @date 2019/3/18 10:28 PM
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        long t1=System.nanoTime();
        Request request=chain.request();
        Log.d("HTTP",String.format("Sending request %s on %s%n%s",
                request.url(),chain.connection(),request.headers()));

        Response response=chain.proceed(request);
        long t2=System.nanoTime();
        Log.d("HTTP",String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    }
}
