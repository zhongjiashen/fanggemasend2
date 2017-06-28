package com.fanggemasend.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.fanggemasend.view.viewpage.BasePage;

import java.util.ArrayList;

/**
 * Created by 1363655717 on 2017/4/27.
 */

public class OrderPageAdapter extends PagerAdapter {
    private String[] mTitles = new String[]{"待抢单", "待取单", "配送中", "已完成"};
    private ArrayList<BasePage> pages;
    public OrderPageAdapter(ArrayList<BasePage> mPagerList) {
        pages=mPagerList;

    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePage pager = pages.get(position);
        container.addView(pager.mRootView);
        // pager.initData();// 初始化数据.... 不要放在此处初始化数据, 否则会预加载下一个页面
        return pager.mRootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
