package com.example.lastyuekao.Net;

import com.example.lastyuekao.Bean.JavaBean;

public interface MainCallBack {

    void onSuccess(JavaBean javaBean);
    void onFail(String string);

}
