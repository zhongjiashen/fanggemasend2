package com.fanggemasend.view.viewpage;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.fanggemasend.adapter.OrderRecycleViewAdapter;
import com.fanggemasend.baserecycleView.BaseRecycleAdapter;
import com.fanggemasend.model.OrderResponse;
import com.fanggemasend.view.activity.TakeDeliveryActivity;

/**
 * 待取订单
 * Created by 1363655717 on 2017/4/27.
 */

public class GetOrdersPage extends BasePage{
    /**
     * 待取订单
     * @param activity
     */
    public GetOrdersPage(Activity activity) {
        super(activity);
    }
    @Override
    public void initViews() {
        viewpage=1;
        adapter = new OrderRecycleViewAdapter(activity, 2);
        super.initViews();
        adapter.setOnItemClickListner(new BaseRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent = intent = new Intent(activity, TakeDeliveryActivity.class).putExtra("id",list.get(position).dl_key)
                        .putExtra("name",list.get(position).m_name)
                        .putExtra("phone",list.get(position).m_iphone)
                        .putExtra("address",list.get(position).m_address);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public void initData(OrderResponse data) {
        super.initData(data);
        Log.e("初始化数据","待取订单");
//        OrderRecycleViewAdapter adapter = new  OrderRecycleViewAdapter(activity ,2);
//        contentView.setAdapter(adapter);
//        adapter.setOnItemClickListner(new BaseRecycleAdapter.OnItemClickListner() {
//            @Override
//            public void onItemClickListner(View v, int position) {
//                Intent intent = intent = new Intent(activity, TakeDeliveryActivity.class);
//                activity.startActivity(intent);
//            }
//        });
    }
}
