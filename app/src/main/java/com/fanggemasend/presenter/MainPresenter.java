package com.fanggemasend.presenter;

import android.app.Activity;

import com.fanggemasend.httputils.Http;
import com.fanggemasend.utils.Tool;
import com.fanggemasend.view.interfaceview.BaseView;

import javax.inject.Inject;

/**
 * Created by 1363655717 on 2017/5/1.
 */

public class MainPresenter {
    private BaseView view;
    private Activity activity;

    @Inject  // 添加注解关键字
    public MainPresenter(BaseView view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    /**
     * 信息查询
     */
    public void getdata(boolean showDialog) {
        Http.MaihnQuery(view, activity, showDialog);
    }

    /**
     * 获取登录数据
     *
     * @param strings
     */
    public void doLogin(String[] strings) {
        Http.Login(strings, view, activity);
    }
}
