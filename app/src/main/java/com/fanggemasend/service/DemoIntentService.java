package com.fanggemasend.service;

/**
 * Created by 1363655717 on 2017/4/4.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.view.activity.OrderActivity;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class DemoIntentService extends GTIntentService {

    public DemoIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        Log.e(TAG,"收到onReceiveServicePid");
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        Intent intent = new Intent(this, OrderActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager nm = (NotificationManager) context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        Uri ringUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notify);
        Notification noti = new NotificationCompat.Builder(context)
                .setTicker("订单消息")
                .setContentTitle("订单消息")
                .setContentText("请及时处理订单消息！")
                .setSmallIcon(R.mipmap.apptu)
                .setContentIntent(pIntent)
                .setSound(ringUri)
                .build();
//            noti.flags = Notification.FLAG_AUTO_CANCEL;  //通知被点击后,可自动消失
        nm.notify(1, noti);
        Log.e(TAG,"收到");
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        Log.e(TAG,"收到onReceiveOnlineState");
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.e(TAG,"收到onReceiveCommandResult");
    }
}