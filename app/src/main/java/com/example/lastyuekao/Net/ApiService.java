package com.example.lastyuekao.Net;

import com.example.lastyuekao.Bean.JavaBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    String BASE_URL = "http://yun918.cn/ks/";

    @GET("jiekou1.json")
    Observable<JavaBean> getJavaBean();

}
