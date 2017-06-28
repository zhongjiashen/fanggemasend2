package com.fanggemasend.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.dragger.component.DaggerMainComponent;
import com.fanggemasend.dragger.module.MainModule;
import com.fanggemasend.presenter.OrderDetailsPresenter;
import com.fanggemasend.utils.PermissionsChecker;
import com.fanggemasend.viewbar.TitleBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017/5/3.
 * 取快递
 */

public class TakeDeliveryActivity extends BaseActivity {
    @BindView(R.id.titler)
    TitleBar titler;
    @BindView(R.id.address_text)
    TextView addressText;
    @BindView(R.id.name_text)
    TextView nameText;
    private String phone;
    private String address;
    @Inject
    OrderDetailsPresenter presenter;
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CALL_PHONE
    };

    private PermissionsChecker mPermissionsChecker; // 权限检测器

    @Override
    public void update(int state, Object bean) {
        Intent intent = new Intent(this, OrderActivity.class).putExtra("page", 2);
        startActivity(intent);
    }

    @Override
    protected int layout() {
        return R.layout.activity_take_delivery;
    }

    @Override
    protected void initview() {
        DaggerMainComponent.builder().mainModule(new MainModule(this, this)).build().inject(this);
        titler.setTitletext("方格马");
        titler.setleft(this, 0);
        String name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        address = getIntent().getStringExtra("address");
        nameText.setText(name + "\n" + phone);
        addressText.setText(address);
        mPermissionsChecker = new PermissionsChecker(this);
    }


    @OnClick({R.id.phone_bt, R.id.address_bt, R.id.bt_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phone_bt:
                if (Build.VERSION.SDK_INT >= 23 && mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                    mPermissionsChecker.requestPermission(this, PERMISSIONS);
                } else {
                    //用intent启动拨打电话
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                    startActivity(intent);
                }
                break;
            case R.id.address_bt:
                break;
            case R.id.bt_ok:
                presenter.OrderProcessing(getIntent().getStringExtra("id"), "B");
                break;
        }
    }
}
