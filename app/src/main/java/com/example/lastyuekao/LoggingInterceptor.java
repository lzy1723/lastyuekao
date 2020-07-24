package com.example.lastyuekao;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
public class LoggingInterceptor implements Interceptor {
    private static final String TAG = "TAG";
    @Override

    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Log.d(TAG, String.format("发送请求地址:%s%n请求头:%s",request.url(),request.headers()));
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        Log.d(TAG, String.format("耗时:%s%n收到来自:%s的结果:%n%s",(endTime-startTime)+"ms",response.request().url(),responseBody.string()));
        return response;
    }
}
