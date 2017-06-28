package com.fanggemasend.view.viewpage;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fanggemasend.adapter.OrderRecycleViewAdapter;
import com.fanggemasend.baserecycleView.BaseRecycleAdapter;
import com.fanggemasend.model.OrderResponse;
import com.fanggemasend.view.activity.OrderActivity;
import com.fanggemasend.view.activity.OrderDetailsActivity;
import com.fanggemasend.view.viewpage.pullableview.MyListener;
import com.fanggemasend.view.viewpage.pullableview.PullToRefreshLayout;

import java.util.List;

/**
 * 待抢订单
 * Created by 1363655717 on 2017/4/27.
 */

public class GrabOrderPage extends BasePage {


    /**
     * 待抢订单
     *
     * @param activity
     */
    public GrabOrderPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initViews() {
        viewpage=0;
        adapter = new OrderRecycleViewAdapter(activity, 1);
        super.initViews();
        adapter.setOnItemClickListner(new BaseRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent = intent = new Intent(activity, OrderDetailsActivity.class).putExtra("id",list.get(position).dl_key);
                activity.startActivity(intent);
            }
        });
    }



    @Override
    public void initData(OrderResponse data) {
        super.initData(data);
    }
}
