package com.fanggemasend.view.viewpage;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.fanggemasend.adapter.OrderRecycleViewAdapter;
import com.fanggemasend.baserecycleView.BaseRecycleAdapter;
import com.fanggemasend.model.OrderResponse;
import com.fanggemasend.view.activity.DeliveryActivity;

/**
 * 配送订单
 * Created by 1363655717 on 2017/4/27.
 */

public class DeliveryOrdersPage extends BasePage{
    /**
     * 配送订单
     * @param activity
     */
    public DeliveryOrdersPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initViews() {
        viewpage=2;
        adapter = new OrderRecycleViewAdapter(activity, 3);
        super.initViews();
        adapter.setOnItemClickListner(new BaseRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent = intent = new Intent(activity, DeliveryActivity.class).putExtra("id",list.get(position).dl_key)
                        .putExtra("name",list.get(position).c_name)
                        .putExtra("phone",list.get(position).c_iphone)
                        .putExtra("address",list.get(position).c_address);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public void initData(OrderResponse data) {
        super.initData(data);
        Log.e("初始化数据","配送订单");
//        OrderRecycleViewAdapter adapter = new  OrderRecycleViewAdapter(activity,3);
//        contentView.setAdapter(adapter);
//        adapter.setOnItemClickListner(new BaseRecycleAdapter.OnItemClickListner() {
//            @Override
//            public void onItemClickListner(View v, int position) {
//                Intent intent = intent = new Intent(activity, DeliveryActivity.class);
//                activity.startActivity(intent);
//            }
//        });
    }
}
