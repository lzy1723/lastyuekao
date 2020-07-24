package com.example.lastyuekao.Model;

import android.util.Log;

import com.example.lastyuekao.Base.BaseModel;
import com.example.lastyuekao.Bean.JavaBean;
import com.example.lastyuekao.LoggingInterceptor;
import com.example.lastyuekao.Net.ApiService;
import com.example.lastyuekao.Net.MainCallBack;
import com.example.lastyuekao.Presenter.MainPresenter;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel extends BaseModel {
    public void setData(MainCallBack mainCallBack) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        Request request = new Request.Builder()
                .url(ApiService.BASE_URL)
                .header("User-Agent", "OkHttp Example")
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();



        ApiService apiService = retrofit.create(ApiService.class);

        Observable<JavaBean> javaBean = apiService.getJavaBean();

        javaBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JavaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JavaBean javaBean) {
                        mainCallBack.onSuccess(javaBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainCallBack.onFail("网络获取失败");
                        Log.i("TAG",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                if (body != null) {
                    Log.d("TAG", "onResponse: " + response.body().string());
                    body.close();
                }
            }
        });

    }
}
