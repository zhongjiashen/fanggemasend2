package com.fanggemasend.presenter;

import android.app.Activity;

import com.fanggemasend.httputils.Http;
import com.fanggemasend.view.interfaceview.BaseView;

import javax.inject.Inject;

/**
 * Created by 1363655717 on 2017/4/26.
 */

public class OrderPresenter {
    private BaseView view;
    private Activity activity;
    @Inject  // 添加注解关键字
    public OrderPresenter(BaseView view, Activity activity) {
        this.view=view;
        this.activity=activity;
    }
    /**
     * 订单信息查询查询
     */
    public void OrderQuery( boolean showDialog,int to_do,int page){
        Http.OrderQuery(view,activity,showDialog,to_do,page);
    }
}
