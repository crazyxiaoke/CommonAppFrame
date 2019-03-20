package com.hz.zxk.lib_commonframe.http.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhengxiaoke
 * @date 2019/3/19 10:49 AM
 */
public class RetryRequestInterceptor implements Interceptor {
    private static final String TAG="HTTP";
    int maxRetry;
    int retryNum=0;

    public RetryRequestInterceptor(){
        this(2);
    }

    public RetryRequestInterceptor(int retryCount){
        this.maxRetry=retryCount;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        Response response=chain.proceed(request);
        Log.d(TAG,"-------网络请求-------");
        while(!response.isSuccessful()&&retryNum<maxRetry){
            Log.d(TAG,"尝试重试请求------"+retryNum);
            retryNum++;
            response=chain.proceed(request);
        }
        return response;
    }
}
