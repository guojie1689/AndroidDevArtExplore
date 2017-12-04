package com.android.devartexplore.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by gj on 2017/12/2.
 */

public class BookManagerService extends Service {

    private RemoteCallbackList<IBookArriveNotListener> notificationListeners = new RemoteCallbackList<>();
    private CopyOnWriteArrayList<Book> books = new CopyOnWriteArrayList<>();
//    private CopyOnWriteArrayList<IBookArriveNotListener> notificationListeners = new CopyOnWriteArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        books.add(new Book(1, "Book1"));
        books.add(new Book(2, "Book2"));

        new notificationThread().start();
    }

    private void notifiNewBookArrive(Book book) throws RemoteException {
        int size = notificationListeners.beginBroadcast();
        for (int i = 0; i < size; i++) {
            IBookArriveNotListener listener = notificationListeners.getBroadcastItem(i);

            if (listener != null) {
                listener.notification(book);
            }
        }

        notificationListeners.finishBroadcast();
    }

    private class notificationThread extends Thread {
        @Override
        public void run() {
            super.run();

            for (int i = 0; i < 3; i++) {
                Book book = new Book(i, "新书" + i);

                try {
                    notifiNewBookArrive(book);
                    Thread.sleep(1000);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private IBinder binder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            books.add(book);
        }

        @Override
        public void registerNotListener(IBookArriveNotListener listener) throws RemoteException {
            notificationListeners.register(listener);
        }

        @Override
        public void unRegisterNotListener(IBookArriveNotListener listener) throws RemoteException {
            notificationListeners.unregister(listener);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}
