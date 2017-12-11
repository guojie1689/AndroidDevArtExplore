package com.android.devartexplore.removeview;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gj on 2017/12/10.
 */

public class RemoteViewActivity extends BaseActivity {

    private NotificationManager mNManager;
    private Notification notify1;

    private static final int NOTIFYID_1 = 1;


    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick(R.id.btn_launch)
    public void showNotification() {

        Intent it = new Intent(this, NotificationActivity.class);
        PendingIntent intent = PendingIntent.getActivity(this, 0, it, 0);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.activity_remote_layout);

        remoteViews.setTextViewText(R.id.txt_info, "title");
        remoteViews.setTextViewText(R.id.txt_info1, "content");

        Notification.Builder mBuilder = new Notification.Builder(this);
        mBuilder.setContentTitle("叶良辰")                     //标题
                .setContentText("我有一百种方法让你呆不下去~")      //内容
                .setSubText("——记住我叫叶良辰")                    //内容下面的一小段文字
                .setTicker("收到叶良辰发送过来的信息~")             //收到信息后状态栏显示的文字信息
                .setWhen(System.currentTimeMillis())           //设置通知时间
                .setSmallIcon(R.mipmap.ic_launcher)            //设置小图标
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
                .setAutoCancel(true)                           //设置点击后取消Notification
                .setContentIntent(intent);                        //设置PendingIntent
        notify1 = mBuilder.build();
        notify1.contentView = remoteViews;
        mNManager.notify(NOTIFYID_1, notify1);

    }

    @BindView(R.id.txt_info)
    TextView txt_info;

    @Override
    protected int proviceContentId() {
        return R.layout.activity_info_with_button;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        txt_info.setText("RemoteView");

        mNManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
}
