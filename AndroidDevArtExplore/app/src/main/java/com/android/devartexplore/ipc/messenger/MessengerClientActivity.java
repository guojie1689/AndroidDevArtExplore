package com.android.devartexplore.ipc.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;
import com.android.devartexplore.ipc.aidl.BookManagerActivity;
import com.android.devartexplore.ipc.binderpool.BinderPoolActivity;
import com.android.devartexplore.ipc.contentprovider.ProviderEntryActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MessengerClientActivity extends BaseActivity {

    @BindView(R.id.btn_askServer)
    Button btn_askServer;

    @OnClick(R.id.btn_content_provider)
    public void toContentProvider() {
        Intent intent = new Intent(this, ProviderEntryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_binder_pool)
    public void toBinderPool() {
        Intent intent = new Intent(this, BinderPoolActivity.class);
        startActivity(intent);
    }

    @Override
    protected int proviceContentId() {
        return R.layout.activity_messenger;
    }

    @OnClick(R.id.btn_askServer)
    public void askServer() {
        Message message = Message.obtain(null, 0);
        message.getData().putString("msg", "Hello Service");
        message.replyTo = replayToMessgner;
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @BindView(R.id.btn_toaidl)
    Button btn_toaidl;

    @OnClick(R.id.btn_toaidl)
    public void toAidl() {
        Intent intent = new Intent(this, BookManagerActivity.class);
        startActivity(intent);
    }

    private Messenger messenger = null;
    private Messenger replayToMessgner = new Messenger(new MessengerClientHandler());

    private class MessengerClientHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String receiveMsg = msg.getData().getString("msg");
                    Log.d("GJ", "msg:" + receiveMsg);
                    break;
            }
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MessengerService.class);

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MessengerClientActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
