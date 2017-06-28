package com.fanggemasend.view.activity;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.viewbar.TitleBar;

import butterknife.BindView;

/**
 * 实名认证正在审核页面
 * Created by 1363655717 on 2017/4/26.
 */

public class RealNameAuthenticationStateActivity extends BaseActivity {
    @BindView(R.id.titler)
    TitleBar titler;

    @Override
    public void update(int state, Object bean) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_real_name_authentication_state;
    }

    @Override
    protected void initview() {
        titler.setTitletext("实名认证");
        titler.setleft(this, 0);
    }
}
