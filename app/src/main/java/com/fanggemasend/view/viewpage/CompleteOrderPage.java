package com.fanggemasend.view.viewpage;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.fanggemasend.adapter.OrderRecycleViewAdapter;
import com.fanggemasend.baserecycleView.BaseRecycleAdapter;
import com.fanggemasend.model.OrderResponse;
import com.fanggemasend.view.activity.OrderDetailsActivity;

/**
 * 完成订单
 * Created by 1363655717 on 2017/4/27.
 */

public class CompleteOrderPage extends BasePage{
    /**
     * 完成订单
     * @param activity
     */
    public CompleteOrderPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initViews() {
        viewpage=3;
        adapter = new OrderRecycleViewAdapter(activity, 4);
        super.initViews();
        adapter.setOnItemClickListner(new BaseRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent = intent = new Intent(activity, OrderDetailsActivity.class).putExtra("id",list.get(position).dl_key).putExtra("kind",1);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public void initData(OrderResponse data) {
        super.initData(data);
        Log.e("初始化数据","完成订单");

    }
}
