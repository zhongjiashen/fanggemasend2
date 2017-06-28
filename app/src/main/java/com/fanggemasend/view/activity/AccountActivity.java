package com.fanggemasend.view.activity;

import android.widget.TextView;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.dragger.component.DaggerMainComponent;
import com.fanggemasend.dragger.module.MainModule;
import com.fanggemasend.presenter.AccountPresenter;
import com.fanggemasend.viewbar.TitleBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 账户
 * Created by 1363655717 on 2017/4/26.
 */

public class AccountActivity extends BaseActivity {
    @Inject
    AccountPresenter presenter;
    @BindView(R.id.titler)
    TitleBar titler;
    @BindView(R.id.account_txt)
    TextView accountTxt;

    @Override
    public void update(int state, Object bean) {
        accountTxt.setText((String)bean);
    }

    @Override
    protected int layout() {
        return R.layout.activity_account;
    }

    @Override
    protected void initview() {
        DaggerMainComponent.builder().mainModule(new MainModule(this, this)).build().inject(this);
        titler.setTitletext("账户");
        titler.setleft(this, 0);
        presenter.AccountBalanceQuery();
    }

    @OnClick(R.id.withdrawal_bt)
    public void onClick() {
    }
}
