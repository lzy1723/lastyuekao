package com.example.lastyuekao.Base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {

    public V mView;

    public ArrayList<BaseModel> mModel = new ArrayList<>();

    public BasePresenter(){
        initModel();
    }

    public void addModel(BaseModel model){
        mModel.add(model);
    }

    protected abstract void initModel();

    public void bindView(V view){
        mView = view;
    }
}
