package com.fanggemasend.httputils;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;

import com.fanggemasend.MyApplication;
import com.fanggemasend.httputils.cach.RxRetrofitCache;
import com.fanggemasend.httputils.rxrequest.RxHelper;
import com.fanggemasend.httputils.rxrequest.RxSubscribe;
import com.fanggemasend.model.GrabOrderDetailsResponse;
import com.fanggemasend.model.MainResponse;
import com.fanggemasend.model.MyResponse;
import com.fanggemasend.model.OrderResponse;
import com.fanggemasend.utils.Tool;
import com.fanggemasend.view.interfaceview.BaseView;
import com.igexin.sdk.PushManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by 1363655717 on 2017/4/24.
 */

public class Http {
    public final static int CACHE_TIME = 10 * 60 * 60;
    public final static String REQUEST_TITLE = "正在加载";

    /**
     * 登录网络请求
     *
     * @param strings
     * @param view
     * @param activity
     */
    public static void Login(String[] strings, final BaseView view, Activity activity) {
        view.showToase(PushManager.getInstance().getClientid(activity));
        try {
            Observable<String> fromNetwrok = MyApplication.application.apiService.login(strings[0], "csadfsadsadhsaufgsdiugfsdhfsiug", "ANZHUO|"+PushManager.getInstance().getClientid(activity), Tool.md5(strings[1]))
                    .compose(RxHelper.<String>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<String>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(String model) {
                            MyApplication.application.spread = model;
                            view.update(0, model);
                            view.showToase("登录成功");

                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }
    /**
     * 登录网络请求
     *
     * @param strings
     * @param view
     * @param activity
     */
    public static void Logina(String[] strings, final BaseView view, Activity activity) {
//        view.showToase(PushManager.getInstance().getClientid(activity));
        try {
            Map map=new HashMap();
            map.put("userName",strings[0]);
            map.put("userPass",Tool.md5(strings[1]));
//            map.put("key","csadfsadsadhsaufgsdiugfsdhfsiug");
//            map.put("uuid",PushManager.getInstance().getClientid(activity));
            Observable<String> fromNetwrok = MyApplication.application.apiService.executePost("UserLogin", map)
                    .compose(RxHelper.<String>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<String>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(String model) {
                            MyApplication.application.spread = model;
                            view.update(0, model);
                            view.showToase("登录成功");

                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);
                            Log.e("FA",message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }

    /**
     * z注册网络请求
     *
     * @param strings
     * @param view
     * @param activity
     */
    public static void Register(String[] strings, final BaseView view, Activity activity) {
        try {
            Observable<String> fromNetwrok = MyApplication.application.apiService.registe(strings[0], "csadfsadsadhsaufgsdiugfsdhfsiug", strings[1], Tool.md5(strings[2]))
                    .compose(RxHelper.<String>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<String>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(String model) {
                            view.update(0, model);
                            view.showToase("注册成功");
                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }

    /**
     * 注册验证码获取网络请求
     *
     * @param strings
     * @param view
     * @param activity
     * @param yzmBt
     */
    public static void GetRegisterVerificationCode(String[] strings, final BaseView view, Activity activity, final Button yzmBt) {
        try {
            Observable<String> fromNetwrok = MyApplication.application.apiService.GetRegisterVerificationCode(strings[0], "csadfsadsadhsaufgsdiugfsdhfsiug", "3")
                    .compose(RxHelper.<String>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<String>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(String model) {
                            view.showToase("获取验证码成功");
                            Tool.ButtonCutDown(120, yzmBt);
                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }
    /**
     * 实名认证状态查询
     * @param view
     * @param activity
     */
//    public static void RealNameAuthenticationAtate(final BaseView view, Activity activity) {
//        try {
//            Log.e("登录标示", MyApplication.application.spread);
//            Observable<String> fromNetwrok = MyApplication.application.apiService.RealNameAuthenticationAtate(MyApplication.application.spread)
//                    .compose(RxHelper.<String>handleResult());
//            RxRetrofitCache.load(fromNetwrok)
//                    .subscribe(new RxSubscribe<String>(activity,
//                            REQUEST_TITLE) {
//                        @Override
//                        protected void _onNext(String model) {
//                            view.update(1, model);
//                        }
//
//                        @Override
//                        protected void _onError(String message) {
//                            view.showToase(message);
//
//                        }
//
//                    });
//        } catch (Exception ex) {
//            view.showToase(ex.toString());
//        }
//    }
    /**
     * 实名认证状态查询
     * @param view
     * @param activity
     */
    public static void RealNameAuthenticationAtate(final BaseView view, Activity activity) {
        try {
            Log.e("登录标示", MyApplication.application.spread);
            Observable<String> fromNetwrok = MyApplication.application.apiService.RealNameAuthenticationAtate(MyApplication.application.spread)
                    .compose(RxHelper.<String>handleResult());
            RxRetrofitCache.load(MyApplication.application.getApplicationContext(),"RealNameAuthenticationAta",0,fromNetwrok,false)
                    .subscribe(new RxSubscribe<String>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(String model) {
                            view.update(1, model);
                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }
    /**
     * 实名认证网络请求
     *
     * @param strings
     * @param z
     * @param f
     * @param view
     * @param activity
     */
    public static void RealNameAuthentication(String[] strings, RequestBody z, RequestBody f, final BaseView view, Activity activity) {
        try {
            Log.e("登录标示", MyApplication.application.spread);
            Observable<String> fromNetwrok = MyApplication.application.apiService.RealNameAuthentication(RequestBody.create(MediaType.parse("multipart/form-data"), strings[0]),RequestBody.create(MediaType.parse("multipart/form-data"), strings[1]),RequestBody.create(MediaType.parse("multipart/form-data"), MyApplication.application.spread),z,f)
                    .compose(RxHelper.<String>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<String>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(String model) {
                            view.update(0, model);
                            view.showToase("实名认证信息提交成功");

                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }

    /**
     * 收款信息设置
     * @param strings
     * @param view
     * @param activity
     */
    public static void GatheringInformationSet(String[] strings, final BaseView view, Activity activity) {
        try {
            Log.e("登录标示", MyApplication.application.spread);
            Observable<String> fromNetwrok = MyApplication.application.apiService.GatheringInformationSet(MyApplication.application.spread,strings[0],strings[1],strings[2],strings[3],strings[4])
                    .compose(RxHelper.<String>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<String>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(String model) {
                            view.update(0, model);
                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }
    /**
     * 账户余额查询
     * @param view
     * @param activity
     */
    public static void AccountBalanceQuery(final BaseView view, Activity activity) {
        try {
            Log.e("登录标示", MyApplication.application.spread);
            Observable<String> fromNetwrok = MyApplication.application.apiService.AccountBalanceQuery(MyApplication.application.spread)
                    .compose(RxHelper.<String>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<String>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(String model) {
                            view.update(1, model);
                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }
    /**
     * 我的信息查询
     * @param view
     * @param activity
     */
    public static void AccountInformationQuery(final BaseView view, Activity activity) {
        
        try {
            Log.e("登录标示", MyApplication.application.spread);
            Observable<MyResponse> fromNetwrok = MyApplication.application.apiService.AccountInformationQuery(MyApplication.application.spread)
                    .compose(RxHelper.<MyResponse>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<MyResponse>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(MyResponse model) {
                            view.update(0, model);
                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }
    /**
     * 我的信息查询
     * @param view
     * @param activity
     * @param showDialog
     */
    public static void MaihnQuery(final BaseView view, Activity activity, boolean showDialog) {
        try {
            Log.e("登录标示", MyApplication.application.spread);
            Observable<MainResponse> fromNetwrok = MyApplication.application.apiService.MaihnQuery(MyApplication.application.spread)
                    .compose(RxHelper.<MainResponse>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<MainResponse>(activity,
                            REQUEST_TITLE,showDialog) {
                        @Override
                        protected void _onNext(MainResponse model) {
                            view.update(1, model);
                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }

    /**
     * 订单信息查询
     * @param view
     * @param activity
     */
    public static void OrderQuery(final BaseView view, Activity activity, boolean showDialog,int to_do,int page) {
        String[] what_to_do=new String[]{"D","A","B","C"};
        try {
            Log.e("登录标示", MyApplication.application.spread);
            Observable<OrderResponse> fromNetwrok = MyApplication.application.apiService.OrderQuery(MyApplication.application.spread,what_to_do[to_do],page+"")
                    .compose(RxHelper.<OrderResponse>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<OrderResponse>(activity,
                            REQUEST_TITLE,showDialog) {
                        @Override
                        protected void _onNext(OrderResponse model) {
                            view.update(0, model);
                        }

                        @Override
                        protected void _onError(String message) {
view.update(9, null);
                            view.showToase(message);
                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }

    public static void GrabOrderDetailsQuery(final BaseView view, Activity activity, String dl_key) {
        try {
            Log.e("登录标示", MyApplication.application.spread);
            Observable<GrabOrderDetailsResponse> fromNetwrok = MyApplication.application.apiService.GrabOrderDetailsQuery(MyApplication.application.spread,dl_key)
                    .compose(RxHelper.<GrabOrderDetailsResponse>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<GrabOrderDetailsResponse>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(GrabOrderDetailsResponse model) {
                            view.update(0, model);
                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }

    public static void OrderProcessing(final BaseView view, Activity activity, String dl_key, String what_to_do) {
        try {
            Log.e("登录标示", MyApplication.application.spread);
            Observable<String> fromNetwrok = MyApplication.application.apiService.OrderProcessing(MyApplication.application.spread,dl_key,what_to_do)
                    .compose(RxHelper.<String>handleResult());
            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<String>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(String model) {
                            view.update(1, model);
                        }

                        @Override
                        protected void _onError(String message) {
                            view.showToase(message);

                        }

                    });
        } catch (Exception ex) {
            view.showToase(ex.toString());
        }
    }
}
