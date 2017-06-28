package com.fanggemasend.presenter;

import android.app.Activity;
import android.util.Log;

import com.fanggemasend.httputils.Http;
import com.fanggemasend.utils.Tool;
import com.fanggemasend.view.interfaceview.BaseView;

import javax.inject.Inject;

/**
 * Created by 1363655717 on 2017/4/24.
 */

public class LoginPresenter {
    private BaseView view;
    private Activity activity;
    @Inject  // 添加注解关键字
    public LoginPresenter(BaseView view, Activity activity) {
        this.view=view;
        this.activity=activity;
        Log.e("dagger","LoginPresenter create!!!");
    }

    /**
     * 获取登录数据
     * @param strings
     */
    public void doLogin(String[] strings){
        if (Tool.isEmpty(strings)) {
            Http.Logina(strings,view,activity);
        } else
            view.showToase("账号密码不能为空！");
    }
    /**
     * 实名认证状态查询
     */
    public void doRealNameAuthenticationState(){
            Http.RealNameAuthenticationAtate(view,activity);
    }
}
