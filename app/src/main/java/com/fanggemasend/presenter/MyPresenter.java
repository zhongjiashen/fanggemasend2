package com.fanggemasend.presenter;

import android.app.Activity;

import com.fanggemasend.httputils.Http;
import com.fanggemasend.view.interfaceview.BaseView;

import javax.inject.Inject;

/**
 * Created by 1363655717 on 2017/4/26.
 */

public class MyPresenter {
    private BaseView view;
    private Activity activity;
    @Inject  // 添加注解关键字
    public MyPresenter(BaseView view, Activity activity) {
        this.view=view;
        this.activity=activity;
    }
    /**
     * 我的信息查询
     */
    public void AccountInformationQuery(){
        Http.AccountInformationQuery(view,activity);
    }
}
