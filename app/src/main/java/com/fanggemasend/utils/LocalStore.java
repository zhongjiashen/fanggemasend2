package com.fanggemasend.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.fanggemasend.MyApplication;

/**
 * 本地数据存储管理类
 * Created by 1363655717 on 2017/4/4.
 */

public class LocalStore {
    public static String phone="phone";
    public static String pass="pass";
    private SharedPreferences sp;
    private Editor et;
    public LocalStore(MyApplication myApplication) {
        sp = myApplication.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        et= sp.edit();
    }

    /**
     * 判断是否首次使用app
     * @return
     */
    public  boolean firstuse(){
        boolean results = sp.getBoolean("firstuse", true);
        et.putBoolean("firstuse", false);
        et.commit();
        return results;
    }

    /**
     * 获取实名状态
     * @return
     */
    public boolean getRealnameStatus(){
       return sp.getBoolean("real_name_status", false);
    }

    /**
     * 保存实名状态
     */
    public void saveRealnameStatus(){
        et.putBoolean("real_name_status", true);
        et.commit();
    }
    public void save(String key,String value){
        et.putString(key, value);
        et.commit();
    }
    public String getsave(String key){
        return sp.getString(key,"");
    }
}
