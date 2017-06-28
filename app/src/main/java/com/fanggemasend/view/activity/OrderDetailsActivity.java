package com.fanggemasend.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.dragger.component.DaggerMainComponent;
import com.fanggemasend.dragger.module.MainModule;
import com.fanggemasend.model.GrabOrderDetailsResponse;
import com.fanggemasend.presenter.OrderDetailsPresenter;
import com.fanggemasend.viewbar.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 订单详情
 * Created by 1363655717 on 2017/5/2.
 */

public class OrderDetailsActivity extends BaseActivity {
    @BindView(R.id.titler)
    TitleBar titler;
    @BindView(R.id.listView)
    ListView listView;

    private FootViewHolder footViewHolder;
    private HeadViewHolder headViewHolder;
    private String dl_key;
    @Inject
    OrderDetailsPresenter presenter;

    @Override
    public void update(int state, Object bean) {
        if (state == 0) {
            GrabOrderDetailsResponse data = (GrabOrderDetailsResponse) bean;
            headViewHolder.sroreNameText.setText(data.name);
            footViewHolder.pickupInformationText.setText(data.b_name + "(先生/女士)" + data.b_phone);
            footViewHolder.pickupAddressText.setText(data.b_address);
            footViewHolder.consigneeInformationText.setText(data.c_name + "(先生/女士)" + data.c_phone);
            footViewHolder.consigneeAddressText.setText(data.c_address);
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < data.date.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", data.date.get(i).name);
                map.put("number", data.date.get(i).number);
                map.put("price", data.date.get(i).price);
                list.add(map);
            }
            listView.setAdapter(new SimpleAdapter(this, list, R.layout.commodity_item,
                    new String[]{"name", "number", "price"}, new int[]{R.id.commodity_name_text, R.id.number_text, R.id.price_text}));
        }
        else {
            Intent intent = new Intent(OrderDetailsActivity.this, OrderActivity.class).putExtra("page",1);
            startActivity(intent);
        }
    }

    @Override
    protected int layout() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void initview() {
        DaggerMainComponent.builder().mainModule(new MainModule(this, this)).build().inject(this);
        dl_key=getIntent().getStringExtra("id");
        presenter.OrderDetailsQuery(dl_key);
        titler.setTitletext("方格马");
        titler.setleft(this, 0);
        View headview = View.inflate(this, R.layout.order_details_head_view, null);
        View footview = View.inflate(this, R.layout.order_details_foot_view, null);
        listView.addHeaderView(headview);
        listView.addFooterView(footview);
        footViewHolder = new FootViewHolder(footview);
        headViewHolder = new HeadViewHolder(headview);
        if(getIntent().getIntExtra("kind",0)==0) {
            footViewHolder.robOrderBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.OrderProcessing(dl_key, "A");
                }
            });
        }else
            footViewHolder.robOrderBt.setVisibility(View.INVISIBLE);

    }


    static class HeadViewHolder {

        @BindView(R.id.srore_name_text)
        TextView sroreNameText;

        HeadViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class FootViewHolder {

        @BindView(R.id.rob_order_bt)
        Button robOrderBt;
        @BindView(R.id.pickup_information_text)
        TextView pickupInformationText;
        @BindView(R.id.pickup_address_text)
        TextView pickupAddressText;
        @BindView(R.id.consignee_information_text)
        TextView consigneeInformationText;
        @BindView(R.id.consignee_address_text)
        TextView consigneeAddressText;

        FootViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
