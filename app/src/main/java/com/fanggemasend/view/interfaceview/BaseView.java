package com.fanggemasend.view.interfaceview;

/**
 * Created by 1363655717 on 2017/3/24.
 */

public interface BaseView{
    /**
     * 显示加载进度对话框
     */
    void showLoadingDialog();
    /**
     * 隐藏加载进度对话框
     */
    void hideLoadingDialog();
    /**
     * 显示吐司
     */
    void showToase(String msg);
    /**
     * 更新
     */
    void update(int state, Object bean);
}
