Android中的IPC方式 

1. 使用Bundle
跨进程传递数据的一种简单形式
2, 使用文件共享
3. 使用Messenger
Messenger可以翻译为信使，通过它可以在不同进程中传递Message对象，在Message中放入我们需要传递的数据，就可以轻松地实现进程间传递了。轻量级的IPC方案，底层实现是AIDL。
以串行的方式处理客户端请求，主要应用场景为进程间传递消息。
底层由AIDL实现
4. AIDL
1） 服务端
创建一个Service监听客户端连接，见BookManagerService.java
创建AIDL文件，声明服务所要提供的接口，见IbookManager.aidl：
2) AndroidMinifest.xml声明Service
<service
            android:name=".ipc.aidl.BookManagerService"
            android:process=":remote"></service>
3） 客户端调用，见MessengerClientActivity.java
由于跨进程调用，如果向服务器Service注册监听事件，需要使用RemoteCallbackList集合实现监听事件管理。
Service中需要注意线程同步。
Service中可以添加权限认证及其它认证形式，如用户身份认证。
5. ContentProvider
底层实现是Binder，由于系统已经做了封装，使用起来非常简单。
1） 继承ContentProvider，实现其onCreate、query、insert、delete、update、getType函数
onCreate -- 初始化工作
getType  -- 返回URI所对应的MIME类型
其它的很好理解
2) AndroidMinifest.xml配置<provider>
<provider
            android:name=".ipc.contentprovider.BookProvider"
            android:authorities="com.android.devartexplore.contentprovier"
            android:process=":provider">
authorities -- ContextProvider唯一标识，外界通过该标识访问  
3） 配置完成，外界可通过URI访问，如：
Uri uri = Uri.parse("content://content://com.android.devartexplore.contentprovier");
4） 通过注册监听事件，监听数据的变化，如调用方添加注册事件
ContentObserver contentObserver = new ContentObserver(handler) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
            }

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
            }
        };
getContentResolver().registerContentObserver(bookUri, false, contentObserver);
getContentResolver().unregisterContentObserver(contentObserver);
5） ContextProvider当数据改变时通知
mContext.getContentResolver().notifyChange(uri, null);
6. BInder连接池
