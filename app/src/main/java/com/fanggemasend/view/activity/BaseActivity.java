package com.fanggemasend.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.fanggema.fanggemasend.R;
import com.fanggemasend.view.interfaceview.BaseView;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private boolean isRequireCheck; // 是否需要系统权限检测, 防止和系统提示框重叠
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());
        ButterKnife.bind(this);

        initview();
    }

    /**
     * 加载布局
     * @return
     */
    protected abstract int layout();

    /**
     * 初始化方法
     */
    protected abstract void initview();


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public void showToase(String msg) {
        Log.e("DSA",msg);
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        if(msg.equals("用户未登录")){
            Intent intent = new Intent(this, LoginActivity.class).putExtra("kind",1);
            startActivity(intent);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 999&&hasAllPermissionsGranted( grantResults)) {
            Log.e("权限","成功");
            isRequireCheck=true;
            agreeAllPermissions();
        }else {
            Log.e("权限","拒绝");
            isRequireCheck = false;
            showMissingPermissionDialog();
        }
    }
    // 含有全部的权限
    public static boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }
    // 显示缺失权限提示
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.help);
        builder.setMessage(R.string.string_help_text);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.setCancelable(false);

        builder.show();
    }
    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

    /**
     * 同意全部权限
     */
    protected void agreeAllPermissions(){

    }
}
