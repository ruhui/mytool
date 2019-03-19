package com.example.administrator.mytoolproject;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface MyApiService {
    /* 登录 */
    @POST("n/login")
    Observable<ResponseParent<LoginResponse>> login(@Body ResquestParent httpClient);
}
