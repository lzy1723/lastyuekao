package com.example.lastyuekao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lastyuekao.Bean.JavaBean;
import com.example.lastyuekao.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<JavaBean.DataBean.ListBean> list;
    private int ITEM_TYPE_ONE = 1;
    private int ITEM_TYPE_TWO = 2;
    public OnMyClickListener onMyClickListener;
    public int mPosition;

    public void setOnMyClickListener(OnMyClickListener onMyClickListener) {
        this.onMyClickListener = onMyClickListener;
    }

    public MyAdapter(Context context, ArrayList<JavaBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_ONE;
        } else {
            return ITEM_TYPE_TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_ONE) {
            View view1 = LayoutInflater.from(context).inflate(R.layout.item_item1, parent, false);
            return new ViewHolder(view1);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_item2, parent, false);
            return new ViewHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == ITEM_TYPE_ONE){
            ViewHolder holder2 = (ViewHolder) holder;
            holder2.tv1.setText("请选择兑换内容");
            holder2.tv2.setText("兑换手续费2.00元");
        }if (itemViewType==ITEM_TYPE_TWO){
            ViewHolder1 holder1 = (ViewHolder1) holder;
            JavaBean.DataBean.ListBean listBean = list.get(position);
            list.get(position).setServicePrice(2);
            holder1.tv_title.setText(list.get(position).getName());
            holder1.tv_stockCount.setText("库存"+list.get(position).getStockCount()+"个");
            holder1.tv_price.setText("销售"+list.get(position).getPrice() + "个");
            holder1.tv_servicePrice.setText(list.get(position).getPrice() + list.get(position).getServicePrice()+"元");
            Glide.with(context).load(list.get(position).getPic()).into(holder1.iv_img);
//            holder1.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onMyClickListener.onMyClick(position);
//                }
//            });
//            holder1.cb_check.setClickable(false);

            holder1.cb_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        mPosition = position;
                        listBean.setIscheck(true);
                        for (int i = 0; i < list.size(); i++) {
                            JavaBean.DataBean.ListBean data = list.get(i);
                            if (i != position){
                                data.setIscheck(false);
                            }
                        }
                        notifyDataSetChanged();
                    }
                }
            });

            holder1.cb_check.setChecked(listBean.isIscheck());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnMyClickListener{
        void onMyClick(int i);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv1;
        public TextView tv2;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv1 = (TextView) rootView.findViewById(R.id.tv1);
            this.tv2 = (TextView) rootView.findViewById(R.id.tv2);
        }

    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public RadioButton cb_check;
        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_stockCount;
        public TextView tv_price;
        public TextView tv_servicePrice;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.cb_check = (RadioButton) rootView.findViewById(R.id.cb_check);
            this.iv_img = (ImageView) rootView.findViewById(R.id.iv_img);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_stockCount = (TextView) rootView.findViewById(R.id.tv_stockCount);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
            this.tv_servicePrice = (TextView) rootView.findViewById(R.id.tv_servicePrice);
        }

    }
}