package com.fanggemasend.adapter;

import android.content.Context;


import com.fanggema.fanggemasend.R;
import com.fanggemasend.baserecycleView.BaseRecycleAdapter;
import com.fanggemasend.baserecycleView.BaseViewHolder;

import com.fanggemasend.model.Baseben;
import com.fanggemasend.model.MainResponse;
import com.fanggemasend.model.OrderResponse;

import java.util.List;

/**
 * Created by wangqi 2016/7/16.
 */
public class OrderRecycleViewAdapter extends BaseRecycleAdapter {

    private int kind;

    public OrderRecycleViewAdapter(Context context, int kind) {
        super(context, R.layout.order_item);
        this.kind = kind;

    }


    @Override
    protected <T extends Baseben> void convert(BaseViewHolder holder, T bean) {
        OrderResponse.RecordBean data = (OrderResponse.RecordBean) bean;
        switch (kind) {
            case 1:
                holder.setText(R.id.state_text, "待抢单");
                holder.setText(R.id.name_text,"收货人："+data.c_name);
                holder.setText(R.id.address_text, "地址："+data.c_address);
                break;
            case 2:
                holder.setText(R.id.state_text, "待取单");
                holder.setText(R.id.name_text,"发货人："+data.m_name);
                holder.setText(R.id.address_text, "地址："+data.m_address);
                break;
            case 3:
                holder.setText(R.id.state_text, "配送中");
                holder.setText(R.id.name_text,"收货人："+data.c_name);
                holder.setText(R.id.address_text, "地址："+data.c_address);
                break;
            case 4:
                holder.setText(R.id.state_text, "已完成");
                holder.setText(R.id.name_text,"收货人："+data.c_name);
                holder.setText(R.id.address_text, "地址："+data.c_address);
                break;
            case 5:
                holder.setText(R.id.state_text, "已超时");
                break;
            default:
                break;
        }

        holder.setText(R.id.money_text,"¥   "+data.money);
    }
}