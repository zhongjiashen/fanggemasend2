package com.fanggemasend.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.dragger.component.DaggerMainComponent;
import com.fanggemasend.dragger.module.MainModule;
import com.fanggemasend.presenter.RegisterPresenter;
import com.fanggemasend.utils.Tool;
import com.fanggemasend.viewbar.TitleBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 注册
 * Created by 1363655717 on 2017/4/4.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.titler)
    TitleBar titler;
    @BindView(R.id.pass_text)
    EditText passText;
    @BindView(R.id.passtwo_text)
    EditText passtwoText;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.yzm_text)
    EditText yzmText;

    @Inject
    RegisterPresenter presenter;
    @BindView(R.id.yzm_bt)
    Button yzmBt;

    @Override
    protected int layout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initview() {
        DaggerMainComponent.builder().mainModule(new MainModule(this, this)).build().inject(this);
        titler.setTitletext("账号注册");
        titler.setleft(this, 0);

    }

    @OnClick({R.id.yzm_bt, R.id.register_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yzm_bt:
                String[] string = new String[]{phone.getText().toString()};
                presenter.GetRegisterVerificationCode(string,yzmBt);
                break;
            case R.id.register_bt:
                if (passText.getText().toString().equals(passtwoText.getText().toString())) {
                    String[] strings = new String[]{phone.getText().toString(), yzmText.getText().toString(), passText.getText().toString()};
                    presenter.doregister(strings);
                } else {
                    Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void update(int state, Object bean) {
        finish();

    }
}
