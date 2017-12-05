// IBinderPool.aidl
package com.android.devartexplore.ipc.binderpool;

// Declare any non-default types here with import statements

interface IBinderPool {
    IBinder queryBinder(int binderCode);
}
