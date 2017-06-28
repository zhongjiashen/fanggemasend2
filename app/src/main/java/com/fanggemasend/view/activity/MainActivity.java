package com.fanggemasend.view.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fanggemasend.MyApplication;
import com.fanggema.fanggemasend.R;
import com.fanggemasend.dragger.component.DaggerMainComponent;
import com.fanggemasend.dragger.module.MainModule;
import com.fanggemasend.model.MainResponse;
import com.fanggemasend.presenter.MainPresenter;
import com.fanggemasend.utils.LocalStore;
import com.fanggemasend.viewbar.TitleBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017/4/26.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.titler)
    TitleBar titler;
    @BindView(R.id.user_location)
    TextView userLocation;
    @Inject
    MainPresenter presenter;
    @BindView(R.id.order_number)
    TextView orderNumber;
    @BindView(R.id.account_text)
    TextView accountText;
    @BindView(R.id.swrefresh)
    SwipeRefreshLayout swrefresh;
    private long firstTime;
    @Override
    public void update(int state, Object bean) {
        if(state==1) {
            MainResponse mainResponse = (MainResponse) bean;
            orderNumber.setText("今日订单（" + mainResponse.orderNum + ")");
            accountText.setText("今日已赚（" + mainResponse.profit + ")");
            swrefresh.setRefreshing(false);
        }else {
            presenter.getdata(true);
        }
    }

    @Override
    protected int layout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        DaggerMainComponent.builder().mainModule(new MainModule(this, this)).build().inject(this);
        titler.setTitletext("主页");
        swrefresh.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        swrefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swrefresh.setRefreshing(true);
                presenter.getdata(false);
            }
        });
        if(getIntent().getIntExtra("stype",0)==1){
            String[] strings=new String[]{MyApplication.application.localStore.getsave(LocalStore.phone), MyApplication.application.localStore.getsave(LocalStore.pass)};
            presenter.doLogin(strings);
        }
    }

    @OnClick({R.id.user_orderform, R.id.user_account, R.id.user_mine, R.id.user_outtimerecord, R.id.bt_ok})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.user_orderform:
                intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.user_account:
                intent = new Intent(this, AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.user_mine:
                intent = new Intent(this, MyActivity.class);
                startActivity(intent);
                break;
            case R.id.user_outtimerecord:
                intent = new Intent(this, OvertimeRecordsActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_ok:
                break;
        }

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(!MyApplication.application.spread.equals("")) {
//            presenter.getdata(true);
//        }
//    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            System.exit(0);
        }
    }
}
