package com.fanggemasend.view.activity;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fanggemasend.MyApplication;
import com.fanggema.fanggemasend.R;
import com.fanggemasend.dragger.component.DaggerMainComponent;
import com.fanggemasend.dragger.module.MainModule;
import com.fanggemasend.presenter.LoginPresenter;
import com.fanggemasend.utils.LocalStore;
import com.fanggemasend.viewbar.TitleBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 登录activity
 * Created by 1363655717 on 2017/3/24.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.psw)
    EditText psw;
    @BindView(R.id.titler)
    TitleBar titler;
    @Inject
    LoginPresenter loginPresenter;
    @BindView(R.id.bt_passwor_back)
    TextView btPassworBack;
    private long firstTime;
    private int kind;
    @Override
    protected int layout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initview() {
        DaggerMainComponent.builder().mainModule(new MainModule(this, this)).build().inject(this);
        titler.setTitletext("方格马(配送版)登录");
        userName.setText(MyApplication.application.localStore.getsave(LocalStore.phone));
        kind=getIntent().getIntExtra("kind",0);
    }


    @OnClick({R.id.long_bt, R.id.bt_register, R.id.bt_passwor_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.long_bt:
                String[] strings = new String[]{userName.getText().toString(), psw.getText().toString()};
                loginPresenter.doLogin(strings);
                break;
            case R.id.bt_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.bt_passwor_back:
                break;
        }
    }


    @Override
    public void update(int state, Object bean) {
        if (state == 0) {
            loginPresenter.doRealNameAuthenticationState();
            MyApplication.application.localStore.save(LocalStore.phone, userName.getText().toString());
            MyApplication.application.localStore.save(LocalStore.pass, psw.getText().toString());
        } else {
            if(kind==0) {
                int i = Integer.parseInt((String) bean);
                Intent intent = null;
                switch (i) {
                    case 0:
                        MyApplication.application.localStore.saveRealnameStatus();
                        intent = new Intent(this, MainActivity.class);
                        break;
                    case 1:
//                    intent = new Intent(this, GatheringInformationSetActivity.class);
                        intent = new Intent(this, RealNameAuthenticationStateActivity.class);
//                    intent = new Intent(this, MainActivity.class);
                        break;
                    case 4:
                        intent = new Intent(this, RealNameAuthenticationActivity.class);
                        break;
                }
                startActivity(intent);
            }
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            System.exit(0);
        }
    }
}
