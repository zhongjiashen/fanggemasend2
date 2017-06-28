package com.fanggemasend.view.activity;

import android.content.Intent;
import android.provider.Settings;
import android.widget.TextView;

import com.fanggemasend.MyApplication;
import com.fanggema.fanggemasend.R;
import com.fanggemasend.dragger.component.DaggerMainComponent;
import com.fanggemasend.dragger.module.MainModule;
import com.fanggemasend.model.MyResponse;
import com.fanggemasend.presenter.MyPresenter;
import com.fanggemasend.utils.LocalStore;
import com.fanggemasend.viewbar.TitleBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017/4/26.
 */

public class MyActivity extends BaseActivity {
    @BindView(R.id.titler)
    TitleBar titler;
    @BindView(R.id.phone_text)
    TextView phoneText;
    @BindView(R.id.name_text)
    TextView nameText;
    @BindView(R.id.yhkh_text)
    TextView yhkhText;
    @BindView(R.id.bank_name)
    TextView bankName;
    @BindView(R.id.zfbname_text)
    TextView zfbnameText;
    @BindView(R.id.zfbzh_text)
    TextView zfbzhText;


    @Inject
    MyPresenter presenter;

    @Override
    public void update(int state, Object bean) {
        phoneText.setText(MyApplication.application.localStore.getsave(LocalStore.phone));
        MyResponse data = (MyResponse) bean;
        nameText.setText(data.bankName);
        yhkhText.setText(data.bankCard);
        bankName.setText(data.afffiliateBank);
        zfbnameText.setText(data.alipayName);
        zfbzhText.setText(data.alipay);

    }

    @Override
    protected int layout() {
        return R.layout.activity_my;
    }

    @Override
    protected void initview() {
        DaggerMainComponent.builder().mainModule(new MainModule(this, this)).build().inject(this);
        titler.setTitletext("我的");
        titler.setleft(this, 0);
        presenter.AccountInformationQuery();
    }

    @OnClick(R.id.quit_bt)
    public void onClick() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);//清空所在栈所有activity
        startActivity(intent);
    }
}
