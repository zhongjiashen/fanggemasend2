package com.fanggemasend.presenter;

import android.app.Activity;

import com.fanggemasend.httputils.Http;
import com.fanggemasend.view.interfaceview.BaseView;

import javax.inject.Inject;

/**
 * Created by 1363655717 on 2017/5/4.
 */

public class OrderDetailsPresenter {
    private BaseView view;
    private Activity activity;
    @Inject  // 添加注解关键字
    public OrderDetailsPresenter(BaseView view, Activity activity) {
        this.view=view;
        this.activity=activity;
    }
    public void OrderDetailsQuery(String dl_key){
        Http.GrabOrderDetailsQuery(view,activity,dl_key);
    }
    public void OrderProcessing(String dl_key,String what_to_do){
        Http.OrderProcessing(view,activity,dl_key,what_to_do);
    }
}
