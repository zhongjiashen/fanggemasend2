package com.fanggemasend.view.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.dragger.component.DaggerMainComponent;
import com.fanggemasend.dragger.module.MainModule;
import com.fanggemasend.presenter.RealNameAuthenticationPresenter;
import com.fanggemasend.utils.PermissionsChecker;
import com.fanggemasend.utils.PictureUtil;
import com.fanggemasend.viewbar.TitleBar;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by 1363655717 on 2017/4/24.
 */

public class RealNameAuthenticationActivity extends BaseActivity {
    @BindView(R.id.titler)
    TitleBar titler;
    @BindView(R.id.sfz_image)
    ImageView sfzImage;
    @BindView(R.id.sfzzm_image)
    ImageView sfzzmImage;
    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.sfzh_et)
    EditText sfzhEt;
    @Inject
    RealNameAuthenticationPresenter presenter;


    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private PermissionsChecker mPermissionsChecker; // 权限检测器


    private static final int SELECT_IMAGE_PATH_F= 10;
    private static final int SELECT_IMAGE_PATH_Z = 11;
    private ArrayList<String> mSelectPath = new ArrayList<>();

    private File z;
    private File f;

    @Override
    public void update(int state, Object bean) {
        Intent intent = intent = new Intent(this, GatheringInformationSetActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int layout() {
        return R.layout.activity_real_name_authentication;
    }

    @Override
    protected void initview() {
        DaggerMainComponent.builder().mainModule(new MainModule(this, this)).build().inject(this);
        titler.setTitletext("实名认证");
        titler.setleft(this, 0);
        mPermissionsChecker = new PermissionsChecker(this);
    }

    @OnClick({R.id.bt_ok, R.id.sfzsc_bt, R.id.sfzzmsc_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_ok:
                if(z==null||f==null){
                    Toast.makeText(this, "请上传证件照！", Toast.LENGTH_SHORT).show();
                }else {
                    String[] strings = new String[]{nameEt.getText().toString(), sfzhEt.getText().toString()};
                    presenter.SubmitInformation(strings, z, f);
                }
                break;
            case R.id.sfzsc_bt:
                if (Build.VERSION.SDK_INT >= 23 && mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                    mPermissionsChecker.requestPermission(this, PERMISSIONS);
                } else {
                    boolean showCamera = true;
                    int maxNum = 1;
                    MultiImageSelector selector = MultiImageSelector.create();
                    selector.showCamera(showCamera);
                    selector.count(maxNum);
                    selector.single();
                    selector.origin((ArrayList<String>) mSelectPath);
                    selector.start(this, SELECT_IMAGE_PATH_F);
                }
                break;
            case R.id.sfzzmsc_bt:
                if (Build.VERSION.SDK_INT >= 23 && mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                    mPermissionsChecker.requestPermission(this, PERMISSIONS);
                } else {
                    boolean showCamera = true;
                    int maxNum = 1;
                    MultiImageSelector selector = MultiImageSelector.create();
                    selector.showCamera(showCamera);
                    selector.count(maxNum);
                    selector.single();
                    selector.origin((ArrayList<String>) mSelectPath);
                    selector.start(this, SELECT_IMAGE_PATH_Z);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE_PATH_Z && resultCode == RESULT_OK) {
            try {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                String path = mSelectPath.get(0);
                Log.e("文件路径", path);
                PictureUtil.uploadpictures(this, new File[]{new File(path)}, new String[]{"a"}, new PictureUtil.PictureUtilListener() {
                    @Override
                    public void onSucces(File[] files) {
                        z=files[0];

                        Bitmap bitmap = BitmapFactory.decodeFile(files[0].getPath());
                        sfzzmImage.setImageBitmap(bitmap);
                        Log.e("文件大小", "" + files[0].length() / 1024);
                    }
                });

            } catch (Exception e) {

            }
        }else if (requestCode == SELECT_IMAGE_PATH_F && resultCode == RESULT_OK) {
            try {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                String path = mSelectPath.get(0);
                Log.e("文件路径", path);
                PictureUtil.uploadpictures(this, new File[]{new File(path)}, new String[]{"b"}, new PictureUtil.PictureUtilListener() {
                    @Override
                    public void onSucces(File[] files) {
                        f=files[0];
                        Bitmap bitmap = BitmapFactory.decodeFile(files[0].getPath());
                        sfzImage.setImageBitmap(bitmap);
                        Log.e("文件大小", "" + files[0].length() / 1024);
                    }
                });

            } catch (Exception e) {

            }
        }
    }
}
