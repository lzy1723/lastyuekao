package com.example.lastyuekao;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_balance)
    EditText edBalance;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.tv_fin)
    TextView tvFin;
    @BindView(R.id.tv_tv1)
    TextView tvTv1;
    @BindView(R.id.tv_set)
    TextView tvSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.btn_ok)
    public void onViewClicked() {
        String s = edBalance.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            int balance = Integer.parseInt(s);
            Intent intent = new Intent(this, ConvertActivity.class);
            intent.putExtra("balance", balance);
            startActivityForResult(intent, 100);
        } else {
            Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            int price = data.getIntExtra("price", 0);
            tvFin.setText("余额为：" + price + "元");
            tvFin.setVisibility(View.VISIBLE);
            tvTv1.setVisibility(View.VISIBLE);
            edBalance.setVisibility(View.INVISIBLE);
            tvSet.setVisibility(View.INVISIBLE);
            btnOk.setVisibility(View.INVISIBLE);
        }
    }
}
