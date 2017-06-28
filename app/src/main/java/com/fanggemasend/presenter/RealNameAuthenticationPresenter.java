package com.fanggemasend.presenter;

import android.app.Activity;
import android.widget.Button;

import com.fanggemasend.httputils.Http;
import com.fanggemasend.utils.Tool;
import com.fanggemasend.view.interfaceview.BaseView;

import java.io.File;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 1363655717 on 2017/4/24.
 */

public class RealNameAuthenticationPresenter {
    private BaseView view;
    private Activity activity;
    @Inject  // 添加注解关键字
    public RealNameAuthenticationPresenter(BaseView view, Activity activity) {
        this.view=view;
        this.activity=activity;
    }

    /**
     * 提交信息
     * @param strings
     * @param z
     * @param f
     */
    public void SubmitInformation(String[] strings, File z, File f){
        if (Tool.isEmpty(strings)) {

            RequestBody requestBody1 =
                    RequestBody.create(MediaType.parse("multipart/form-data"), z);
            RequestBody requestBody2 =
                    RequestBody.create(MediaType.parse("multipart/form-data"), f);
            Http.RealNameAuthentication( strings, requestBody1,  requestBody2,view,activity);
        } else
            view.showToase("信息填写不完整！");
    }

}
