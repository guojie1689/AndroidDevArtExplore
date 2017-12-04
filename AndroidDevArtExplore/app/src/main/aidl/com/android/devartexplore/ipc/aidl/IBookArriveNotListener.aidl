// IBookManager.aidl
package com.android.devartexplore.ipc.aidl;

// Declare any non-default types here with import statements

import com.android.devartexplore.ipc.aidl.Book;

interface IBookArriveNotListener {
    void notification(in Book book);
}
