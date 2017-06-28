package com.fanggemasend.presenter;

import android.app.Activity;

import com.fanggemasend.httputils.Http;
import com.fanggemasend.view.interfaceview.BaseView;

import javax.inject.Inject;

/**
 * Created by 1363655717 on 2017/4/26.
 */

public class AccountPresenter {
    private BaseView view;
    private Activity activity;
    @Inject  // 添加注解关键字
    public AccountPresenter(BaseView view, Activity activity) {
        this.view=view;
        this.activity=activity;
    }
    /**
     * 实名认证状态查询
     */
    public void AccountBalanceQuery(){
        Http.AccountBalanceQuery(view,activity);
    }
}
