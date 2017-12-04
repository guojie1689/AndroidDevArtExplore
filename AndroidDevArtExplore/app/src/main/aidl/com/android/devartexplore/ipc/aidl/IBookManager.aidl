// IBookManager.aidl
package com.android.devartexplore.ipc.aidl;

// Declare any non-default types here with import statements

import com.android.devartexplore.ipc.aidl.Book;
import com.android.devartexplore.ipc.aidl.IBookArriveNotListener;

interface IBookManager {
    List<Book> getBookList();

    void addBook(in Book book);

    void registerNotListener(in IBookArriveNotListener listener);

    void unRegisterNotListener(in IBookArriveNotListener listener);
}
