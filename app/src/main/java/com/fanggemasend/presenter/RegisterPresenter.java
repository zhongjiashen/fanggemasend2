package com.fanggemasend.presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;

import com.fanggemasend.httputils.Http;
import com.fanggemasend.utils.Tool;
import com.fanggemasend.view.interfaceview.BaseView;

import javax.inject.Inject;

/**
 * Created by 1363655717 on 2017/4/24.
 */

public class RegisterPresenter {
    private BaseView view;
    private Activity activity;
    @Inject  // 添加注解关键字
    public RegisterPresenter(BaseView view, Activity activity) {
        this.view=view;
        this.activity=activity;
    }

    /**
     * 注册
     * @param strings
     */
    public void doregister(String[] strings){
        if (Tool.isEmpty(strings)) {
            Http.Register(strings,view,activity);
        } else
            view.showToase("信息填写不完整！");
    }

    /**
     * 获取注册验证码
     * @param strings
     * @param yzmBt
     */
    public void GetRegisterVerificationCode(String[] strings,Button yzmBt){
        if (Tool.isEmpty(strings)) {
            Http.GetRegisterVerificationCode(strings,view,activity,yzmBt);
        } else
            view.showToase("请输入电话号码！");
    }
}
