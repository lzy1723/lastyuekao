package com.example.lastyuekao;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastyuekao.Adapter.MyAdapter;
import com.example.lastyuekao.Base.BaseActivity;
import com.example.lastyuekao.Bean.JavaBean;
import com.example.lastyuekao.Presenter.MainPresenter;
import com.example.lastyuekao.View.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConvertActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_phone_ok)
    EditText edPhoneOk;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_convert)
    Button btnConvert;
    private ArrayList<JavaBean.DataBean.ListBean> list;
    private MyAdapter myAdapter;
    private int mPosition;
    private int balance;
    private Intent intent;

    @Override
    protected int initLayout() {
        return R.layout.activity_convert;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();

        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        myAdapter = new MyAdapter(this, list);
        rlv.setAdapter(myAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.setData();
    }

    @Override
    protected void initListener() {
        intent = getIntent();
        balance = intent.getIntExtra("balance", 0);
        tvBalance.setText("账户余额："+ balance +"元");

//        myAdapter.setOnMyClickListener(new MyAdapter.OnMyClickListener() {
//            @Override
//            public void onMyClick(int i) {
//                mPosition = i;
//                for (int j = 0; j < list.size(); j++) {
//                    if (j == i){
//                        list.get(j).setIscheck(true);
//                    } else {
//                        list.get(j).setIscheck(false);
//                    }
//                }
//                myAdapter.notifyDataSetChanged();
//            }
//        });
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    public void setData(JavaBean javaBean) {
        List<JavaBean.DataBean.ListBean> listBeans = javaBean.getData().getList();
        list.addAll(listBeans);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_cancel, R.id.btn_convert})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                Toast.makeText(this, "已退出兑换页", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btn_convert:
                String phone = edPhone.getText().toString();
                String phoneok = edPhoneOk.getText().toString();

                JavaBean.DataBean.ListBean listBean = list.get(myAdapter.mPosition);
                int servicePrice = listBean.getServicePrice();
                int beanPrice = listBean.getPrice();

                int MyPrice = servicePrice + beanPrice;

                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(phoneok)){
                    if (phone.equals(phoneok)){
                        if (listBean.getStockCount() > 0){
                            if (balance > listBean.getServicePrice()){
                                int Price = balance - MyPrice;
                                intent.putExtra("price",Price);
                                setResult(200,intent);
                                finish();
                            }else {
                                Toast.makeText(this, "余额不足，请充值", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(this, "该套餐已售罄，请选择其他套餐！！！", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this, "两次手机号不一致！！！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "手机号不能为空！！！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
