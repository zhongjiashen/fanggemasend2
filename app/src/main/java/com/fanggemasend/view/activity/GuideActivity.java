package com.fanggemasend.view.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;


import com.fanggema.fanggemasend.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：申中佳
 * Time 16/8/7.
 * Description:引导页
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    @BindView(R.id.vp_guide)
    ViewPager vpGuide;
    // 开始体验按钮
    @BindView(R.id.btn_guide_start_experience)
    Button btnGuideStartExperience;
    // 点的组
    @BindView(R.id.ll_guide_point_group)
    LinearLayout llGuidePointGroup;
    // 选中的点view对象
    @BindView(R.id.select_point)
    View selectPoint;
    // ViewPager的数据
    private List<ImageView> imageViewList;
    // 点的组
//    private LinearLayout llPointGroup;
    // 选中的点view对象
//    private View mSelectPointView;
    // 开始体验按钮
//    private Button btnStartExperience;
    // ViewPager

    // 点之间的宽度
    private int pWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去标题, 需要在setContentView方法之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initView();// 初始化控件
    }

    /**
     * 初始化控件
     */
    private void initView() {

//        llPointGroup = (LinearLayout) findViewById(R.id.ll_guide_point_group);
//        mSelectPointView = findViewById(R.id.select_point);
        initData();// 初始化ViewPager数据
        GuideAdapter adapter = new GuideAdapter();
        vpGuide.setAdapter(adapter);
        vpGuide.addOnPageChangeListener(this);/*setOnPageChangeListener(this);*/// 设置监听器
        btnGuideStartExperience.setOnClickListener(this);// 按钮添加监听
    }

    /**
     * TODO：初始化ViewPager数据 void
     */
    private void initData() {
        int[] imageResIDs = {R.mipmap.guide1_1080, R.mipmap.guide2_1080,
                R.mipmap.guide3_1080, R.mipmap.guide4_1080};
        imageViewList = new ArrayList<>();

        ImageView iv;// 图片
        View view;// 点
        LayoutParams params; // 参数类

        for (int i = 0; i < imageResIDs.length; i++) {
            iv = new ImageView(this);
            iv.setBackgroundResource(imageResIDs[i]);
            imageViewList.add(iv);
            // 根据图片的个数, 每循环一次向LinearLayout中添加一个点
            view = new View(this);
            view.setBackgroundResource(R.drawable.point_normal);
            // 设置参数
            params = new LayoutParams(10, 10);
            if (i != 0) {
                params.leftMargin = 10;
            }
            view.setLayoutParams(params);// 添加参数
            llGuidePointGroup.addView(view);
        }
    }


    /**
     * 当页面正在滚动时 position 当前选中的是哪个页面 positionOffset 比例 positionOffsetPixels 偏移像素
     */
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

        Log.e("zejian", "positionOffset:-->" + positionOffset);
        Log.e("zejian", "position:-->" + position);
        //获取两个点间的距离,获取一次即可
        if (pWidth == 0) {
            pWidth = llGuidePointGroup.getChildAt(1).getLeft()
                    - llGuidePointGroup.getChildAt(0).getLeft();
        }

        // 获取点要移动的距离
        int leftMargin = (int) (pWidth * (position + positionOffset));
        // 给红点设置参数
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) selectPoint
                .getLayoutParams();
        params.leftMargin = leftMargin;
        selectPoint.setLayoutParams(params);
    }

    /**
     * 当页面被选中
     */
    @Override
    public void onPageSelected(int position) {
        // 显示体验按钮
        if (position == imageViewList.size() - 1) {
            btnGuideStartExperience.setVisibility(View.VISIBLE);// 显示
        } else {
            btnGuideStartExperience.setVisibility(View.GONE);// 隐藏
        }
    }

    /**
     * 当页面滚动状态改变
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 打开新的界面
     */
    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }


    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /*
         * 删除元素
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViewList.get(position);
            container.addView(iv);// 1. 向ViewPager中添加一个view对象
            return iv; // 2. 返回当前添加的view对象
        }
    }
}