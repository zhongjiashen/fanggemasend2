package com.fanggemasend.view.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.adapter.OrderPageAdapter;
import com.fanggemasend.dragger.component.DaggerMainComponent;
import com.fanggemasend.dragger.module.MainModule;
import com.fanggemasend.model.OrderResponse;
import com.fanggemasend.presenter.OrderPresenter;
import com.fanggemasend.view.viewpage.BasePage;
import com.fanggemasend.view.viewpage.CompleteOrderPage;
import com.fanggemasend.view.viewpage.DeliveryOrdersPage;
import com.fanggemasend.view.viewpage.GetOrdersPage;
import com.fanggemasend.view.viewpage.GrabOrderPage;
import com.fanggemasend.viewbar.TitleBar;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017/4/27.
 */

public class OrderActivity extends BaseActivity {
    private String[] what_to_do=new String[]{"A","B","C","D"};

    @BindView(R.id.titler)
    TitleBar titler;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private ArrayList<BasePage> mPagerList;
    private boolean[] first_initdata=new boolean[]{true,true,true,true};
    @Inject
    OrderPresenter presenter;
    @Override
    public void update(int state, Object bean) {
        if(state==9)
            mPagerList.get(viewPager.getCurrentItem()).initData(null);// 获取当前被选中的页面, 初始化该页面数据
        else {
            OrderResponse data = (OrderResponse) bean;

            mPagerList.get(viewPager.getCurrentItem()).initData(data);// 获取当前被选中的页面, 初始化该页面数据
        }
    }

    @Override
    protected int layout() {
        return R.layout.activity_order;
    }

    @Override
    protected void initview() {
        Log.e("OrderActivity","的initview()");
        DaggerMainComponent.builder().mainModule(new MainModule(this, this)).build().inject(this);
        titler.setTitletext("订单");
        titler.setleft(this, 0);
        // 初始化4个子页面
        mPagerList = new ArrayList<BasePage>();
        mPagerList.add(new GrabOrderPage(this));
        mPagerList.add(new GetOrdersPage(this));
        mPagerList.add(new DeliveryOrdersPage(this));
        mPagerList.add(new CompleteOrderPage(this));
        viewPager.setAdapter(new OrderPageAdapter(mPagerList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                if (first_initdata[arg0]) {
                    getOrderListQuery(true, arg0, 1);

                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        // 初始化首页数据
        getOrderListQuery(true,0,1);

    }

    /**
     * 订单列表信息查询
     * @param showDialog 是否显示加载对话框
     * @param i  类别
     * @param page  页码
     */
    public void getOrderListQuery(boolean showDialog,int i,int page){
        presenter.OrderQuery(showDialog,i, page);
        first_initdata[i]=false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        first_initdata=new boolean[]{true,true,true,true};
        int i=intent.getIntExtra("page",0);
        if(i==viewPager.getCurrentItem()) {
            getOrderListQuery(true, i, 1);
            Log.e("OrderActivity","1");
        }else
            viewPager.setCurrentItem(i);
        Log.e("OrderActivity","的onNewIntent()");
    }

    @Override
    public void showToase(String msg) {
        super.showToase(msg);

    }
    @Override
    protected void onResume() {
        super.onResume();
        NotificationManager nm = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        nm.cancel(1);
    }
}
