package com.fanggemasend.presenter;

import android.app.Activity;

import com.fanggemasend.httputils.Http;
import com.fanggemasend.utils.Tool;
import com.fanggemasend.view.interfaceview.BaseView;

import java.io.File;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 1363655717 on 2017/4/26.
 */

public class GatheringInformationSetPresenter {
    private BaseView view;
    private Activity activity;
    @Inject  // 添加注解关键字
    public GatheringInformationSetPresenter(BaseView view, Activity activity) {
        this.view=view;
        this.activity=activity;
    }
    /**
     * 提交信息
     * @param strings
     */
    public void SubmitInformation(String[] strings){
        if (Tool.isEmpty(strings)) {
            Http.GatheringInformationSet( strings,view,activity);
        } else
            view.showToase("信息填写不完整！");
    }
}
