package com.android.devartexplore.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;
import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gj on 2017/12/2.
 */

public class BookManagerActivity extends BaseActivity {

    @BindView(R.id.btn_query)
    Button btn_query;

    @OnClick(R.id.btn_query)
    public void queryBooks() {
        if (bookManager != null) {
            try {
                List<Book> books = bookManager.getBookList();
                if (books != null) {
                    ToastUtils.showShort(books.size() + "本书");
                }

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @BindView(R.id.btn_add)
    Button btn_add;

    @OnClick(R.id.btn_add)
    public void addBook() {
        try {
            bookManager.addBook(new Book(3, "book3"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private Handler msgHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Book book = (Book) msg.obj;

            ToastUtils.showShort(book.bookName);

        }
    };

    private IBookArriveNotListener listener = new IBookArriveNotListener.Stub() {

        @Override
        public void notification(Book book) throws RemoteException {
            Message msg = Message.obtain();
            msg.obj = book;

            msgHandler.sendMessage(msg);
        }
    };

    private IBookManager bookManager = null;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = IBookManager.Stub.asInterface(service);

            try {
                bookManager.asBinder().linkToDeath(mDeathRecipient, 0);
                bookManager.registerNotListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            try {
                bookManager.unRegisterNotListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


    };


    @Override
    protected int proviceContentId() {
        return R.layout.activity_ipc_bookmanager;
    }

    //Binder死亡代理，Binder死亡的时候就可以收到通知
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (bookManager == null) {
                return;
            }

            bookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            bookManager = null;

            //此时可以重连service

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, BookManagerService.class);


        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
