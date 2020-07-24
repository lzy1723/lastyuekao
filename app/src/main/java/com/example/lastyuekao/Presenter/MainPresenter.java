package com.example.lastyuekao.Presenter;

import com.example.lastyuekao.Base.BasePresenter;
import com.example.lastyuekao.Bean.JavaBean;
import com.example.lastyuekao.Model.MainModel;
import com.example.lastyuekao.Net.MainCallBack;
import com.example.lastyuekao.View.MainView;

public class MainPresenter extends BasePresenter<MainView> implements MainCallBack {


    private MainModel mMainModel;

    @Override
    public void onSuccess(JavaBean javaBean) {
        mView.setData(javaBean);
    }

    @Override
    public void onFail(String string) {
        mView.showToast(string);
    }

    @Override
    protected void initModel() {
        mMainModel = new MainModel();
        addModel(mMainModel);
    }

    public void setData() {
        mMainModel.setData(this);
    }
}
