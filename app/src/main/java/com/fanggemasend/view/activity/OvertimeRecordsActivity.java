package com.fanggemasend.view.activity;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.adapter.OrderRecycleViewAdapter;
import com.fanggemasend.model.MainResponse;
import com.fanggemasend.model.OrderResponse;
import com.fanggemasend.view.viewpage.pullableview.MyListener;
import com.fanggemasend.view.viewpage.pullableview.PullToRefreshLayout;
import com.fanggemasend.view.viewpage.pullableview.PullableRecyclerView;
import com.fanggemasend.viewbar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017/5/2.
 * 超时记录
 */

public class OvertimeRecordsActivity extends BaseActivity {
    @BindView(R.id.titler)
    TitleBar titler;
    @BindView(R.id.content_view)
    PullableRecyclerView contentView;
    @BindView(R.id.refresh_view)
    PullToRefreshLayout refreshView;
    List<OrderResponse.RecordBean> list=new ArrayList<>();
    @Override
    public void update(int state, Object bean) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_overtime_records;
    }

    @Override
    protected void initview() {
        titler.setTitletext("超时记录");
        titler.setleft(this, 0);
        refreshView.setOnRefreshListener(new MyListener());
        OrderRecycleViewAdapter adapter = new  OrderRecycleViewAdapter(this,5);
        contentView.setAdapter(adapter);
    }
}
