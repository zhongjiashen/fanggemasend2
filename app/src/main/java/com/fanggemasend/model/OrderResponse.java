package com.fanggemasend.model;

import java.util.List;

/**
 * Created by 1363655717 on 2017/5/4.
 */

public class OrderResponse {
    public int howmanypages;
    public List<RecordBean> record;

    public static class RecordBean extends Baseben{
        public String dl_key;
        public String phonenumber;
        public String m_address;//商家地址
        public double money;
        public String m_name;//商家姓名
        public String m_iphone;//商家电话
        public String c_name;//用户姓名
        public String c_address;//用户地址
        public String c_iphone;//用户电话
    }
}
