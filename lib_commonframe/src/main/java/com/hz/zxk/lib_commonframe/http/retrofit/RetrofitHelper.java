package com.hz.zxk.lib_commonframe.http.retrofit;

import android.content.Context;
import android.util.Log;

import com.hz.zxk.lib_commonframe.http.ApiService;
import com.hz.zxk.lib_commonframe.http.compose.SchedulersCompose;
import com.hz.zxk.lib_commonframe.http.interceptor.CacheInterceptor;
import com.hz.zxk.lib_commonframe.http.interceptor.LoggingInterceptor;
import com.hz.zxk.lib_commonframe.http.interceptor.RetryRequestInterceptor;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhengxiaoke
 * @date 2019/3/18 9:14 PM
 */
public class RetrofitHelper {

    private OkHttpClient mHttpClient;
    private Retrofit mRetrofit;

    private static Context sContext;

    private static String sBaseUrl;

    private static RetrofitHelper sInstance;

    private RetrofitHelper(String baseUrl) {
        if (baseUrl == null || baseUrl.trim().length() == 0) {
            throw new IllegalArgumentException("请在Application中初始化baseUrl" +
                    "或者传入baseUrl");
        }
        initClient();
        initRetrofit();
    }

    private RetrofitHelper() {
        this(sBaseUrl);
    }


    public static RetrofitHelper getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitHelper();
                }
            }
        }
        return sInstance;
    }

    public static RetrofitHelper getInstance(String baseUrl) {
        return new RetrofitHelper(baseUrl);
    }

    public static void init(Context context,String baseUrl) {
        sBaseUrl = baseUrl;
        sContext=context;
    }

    /**
     * 创建OkHttpClient
     */
    private void initClient() {
        mHttpClient = new OkHttpClient.Builder()
                .cache(getCache())  //设置缓存
                .addInterceptor(new CacheInterceptor(sContext))
                .addInterceptor(new LoggingInterceptor())  //日志拦截器
                .addInterceptor(new RetryRequestInterceptor()) //网络错误重试拦截器，默认重试2次
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 设置缓存目录，缓存大小
     * @return
     */
    private Cache getCache(){
        Log.d("HTTP","cacheDir="+sContext.getCacheDir().getAbsolutePath());
        File file=new File(sContext.getCacheDir(),"responses"); //缓存目录
        int cacheSize=10*1024*1024; //缓存10M
        Cache cache=new Cache(file,cacheSize);
        return cache;

    }

    /**
     * 创建Retrofit
     */
    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .client(mHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(sBaseUrl)
                .build();
    }


    /**
     * Get请求方式
     * @param url
     * @param queryMap
     * @param observer
     * @return
     */
    public void get(String url, Map<String, Object> queryMap, Observer<ResponseBody> observer) {
        mRetrofit.create(ApiService.class)
                .get(url, queryMap)
                .compose(SchedulersCompose.<ResponseBody>rxSchedulers())
                .subscribe(observer);
    }

    /**
     * Get请求方式
     * @param url
     * @param queryMap
     * @return Observable
     */
    public Observable<ResponseBody> get(String url,Map<String,Object> queryMap){
        return mRetrofit.create(ApiService.class)
                .get(url,queryMap);
    }

    /**
     * post请求
     * @param url
     * @param fieldMap
     * @param observer
     */
    public void post(String url,Map<String,Object> fieldMap,Observer<ResponseBody> observer){
        mRetrofit.create(ApiService.class)
                .get(url,fieldMap)
                .compose(SchedulersCompose.<ResponseBody>rxSchedulers())
                .subscribe(observer);
    }

    /**
     *
     * @param url
     * @param fieldMap
     * @return Observable
     */
    public Observable<ResponseBody> post(String url,Map<String,Object> fieldMap){
        return mRetrofit.create(ApiService.class)
                .post(url,fieldMap);
    }

    /**
     * 使用自定义的APIService
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T exceuteApi(Class<T> clazz){
        return mRetrofit.create(clazz);
    }

}
