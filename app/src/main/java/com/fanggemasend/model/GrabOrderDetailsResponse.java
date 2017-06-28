package com.fanggemasend.model;

import java.util.List;

/**
 * Created by 1363655717 on 2017/5/4.
 * 待抢订单详情数据
 */

public class GrabOrderDetailsResponse {

    public String c_phone;
    public String b_name;
    public String c_address;
    public String b_address;
    public String name;
    public String b_phone;
    public String c_name;
    public List<DateBean> date;

    public static class DateBean {
        /**
         * price : 0.01
         * name : 手机
         * number : 1
         */

        public double price;
        public String name;
        public int number;
    }

}
