package com.fanggemasend.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.viewbar.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 完成订单详情
 * Created by 1363655717 on 2017/5/2.
 */

public class CompleteOrderDetailsActivity extends BaseActivity {
    @BindView(R.id.titler)
    TitleBar titler;
    @BindView(R.id.listView)
    ListView listView;

    @Override
    public void update(int state, Object bean) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void initview() {
        titler.setTitletext("方格马");
        titler.setleft(this, 0);
        View headview = View.inflate(this, R.layout.order_details_head_view, null);
        View footview = View.inflate(this, R.layout.order_details_foot_view, null);
        listView.addHeaderView(headview);
        listView.addFooterView(footview);
        ViewHolder fooholder=new ViewHolder(footview);
        fooholder.robOrderBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompleteOrderDetailsActivity.this, OrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "米线" + i);
            map.put("number", "X" + i);
            map.put("price", "¥ 1" + i);
            data.add(map);
        }
        listView.setAdapter(new SimpleAdapter(this, data, R.layout.commodity_item,
                new String[]{"name", "number", "price"}, new int[]{R.id.commodity_name_text, R.id.number_text, R.id.price_text}));
    }


    static class ViewHolder {
        @BindView(R.id.rob_order_bt)
        Button robOrderBt;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
