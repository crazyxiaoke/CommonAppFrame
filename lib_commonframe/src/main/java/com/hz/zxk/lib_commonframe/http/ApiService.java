package com.hz.zxk.lib_commonframe.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author zhengxiaoke
 * @date 2019/3/18 9:38 PM
 */
public interface ApiService {


    @GET("{url}")
    Observable<ResponseBody> get(@Path(value = "url",encoded = true) String url,
                                 @QueryMap Map<String,Object> queryMap);

    @POST("{url}")
    @FormUrlEncoded
    Observable<ResponseBody> post(@Path(value="url",encoded = true) String url,
                              @FieldMap Map<String,Object> fieldMap);


}
