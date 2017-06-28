package com.fanggemasend.view.viewpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.adapter.OrderRecycleViewAdapter;
import com.fanggemasend.baserecycleView.BaseRecycleAdapter;
import com.fanggemasend.model.MainResponse;
import com.fanggemasend.model.OrderResponse;
import com.fanggemasend.view.activity.OrderActivity;
import com.fanggemasend.view.activity.OrderDetailsActivity;
import com.fanggemasend.view.viewpage.pullableview.MyListener;
import com.fanggemasend.view.viewpage.pullableview.PullToRefreshLayout;
import com.fanggemasend.view.viewpage.pullableview.PullableRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;

/**
 * Created by 1363655717 on 2017/4/27.
 */

public abstract class BasePage {
    OrderRecycleViewAdapter adapter;
    protected int howmanypages;//总共多少也
    protected boolean type = true; //是刷新还加加载更多
    protected int page=1;//当前页码
    protected int viewpage=0;//当前view页码
    public View mRootView;// 布局对象
    public FrameLayout flContent;// 内容
    protected Activity activity;
    protected PullToRefreshLayout refreshView;
    protected PullableRecyclerView contentView;
    List<OrderResponse.RecordBean> list=new ArrayList<>();
    public BasePage(Activity activity) {
        this.activity = activity;
        initViews();
    }

    /**
     * 初始化布局
     */
    public void initViews() {
        mRootView = View.inflate(activity, R.layout.view_page, null);
        refreshView = (PullToRefreshLayout) mRootView.findViewById(R.id.refresh_view);
        contentView= (PullableRecyclerView) mRootView.findViewById(R.id.content_view);
        contentView.setAdapter(adapter);
        refreshView.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                page=1;
                OrderActivity orderActivity= (OrderActivity) activity;
                orderActivity.getOrderListQuery(false,viewpage,page);
                type=true;

            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                page=page+1;
                if (howmanypages>page) {
                    type=false;
                    OrderActivity orderActivity = (OrderActivity) activity;
                    orderActivity.getOrderListQuery(false, 0, page);
                }else {
                    // 千万别忘了告诉控件加载完毕了哦！
                    refreshView.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    Toast.makeText(activity,"没有更多数据！",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    /**
     * 数据加载
     * @param data
     */
    public void initData(OrderResponse data){
        if(data==null){
            if (type) {
                // 千万别忘了告诉控件刷新完毕了哦！
                refreshView.refreshFinish(PullToRefreshLayout.SUCCEED);
            } else {
                // 千万别忘了告诉控件加载完毕了哦！
                refreshView.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        }else {
            howmanypages = data.howmanypages;
            if (type) {
                // 千万别忘了告诉控件刷新完毕了哦！
                refreshView.refreshFinish(PullToRefreshLayout.SUCCEED);
                list = data.record;
                adapter.addList(list);
            } else {
                // 千万别忘了告诉控件加载完毕了哦！
                refreshView.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                list.addAll(data.record);
                adapter.addList(list);
            }
            adapter.notifyDataSetChanged();
        }
    }

}
