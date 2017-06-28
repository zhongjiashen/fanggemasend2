package com.fanggemasend.dragger.component;




import android.app.Activity;

import com.fanggemasend.dragger.module.MainModule;
import com.fanggemasend.view.activity.AccountActivity;
import com.fanggemasend.view.activity.DeliveryActivity;
import com.fanggemasend.view.activity.GatheringInformationSetActivity;
import com.fanggemasend.view.activity.LoginActivity;
import com.fanggemasend.view.activity.MainActivity;
import com.fanggemasend.view.activity.MyActivity;
import com.fanggemasend.view.activity.OrderActivity;
import com.fanggemasend.view.activity.OrderDetailsActivity;
import com.fanggemasend.view.activity.RealNameAuthenticationActivity;
import com.fanggemasend.view.activity.RegisterActivity;
import com.fanggemasend.view.activity.TakeDeliveryActivity;

import dagger.Component;

/**
 * Created by xz on 2016/7/13.
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    public void inject(LoginActivity activity) ;
    void inject(RegisterActivity registerActivity);

    void inject(RealNameAuthenticationActivity realNameAuthenticationActivity);

    void inject(GatheringInformationSetActivity gatheringInformationSetActivity);

    void inject(AccountActivity accountActivity);

    void inject(MyActivity myActivity);

    void inject(MainActivity mainActivity);

    void inject(OrderActivity orderActivity);

    void inject(OrderDetailsActivity orderDetailsActivity);

    void inject(TakeDeliveryActivity takeDeliveryActivity);

    void inject(DeliveryActivity deliveryActivity);
}
