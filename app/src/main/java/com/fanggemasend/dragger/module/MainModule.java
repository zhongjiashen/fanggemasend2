package com.fanggemasend.dragger.module;


import android.app.Activity;

import com.fanggemasend.view.interfaceview.BaseView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xz on 2016/7/13.
 */

@Module
public class MainModule {
    private final BaseView view ;
    private  Activity activity ;
    public MainModule(BaseView view,Activity activity){
        this.view = view ;
        this.activity=activity;


    }

    @Provides
    BaseView provideILogView(){
        return view ;
    }
    @Provides
    Activity provideActivity(){
        return activity ;
    }


}
