package com.android.devartexplore.ipc.contentprovider;

import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;
import com.android.devartexplore.ipc.aidl.Book;
import com.blankj.utilcode.util.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gj on 2017/12/4.
 */

public class ProviderEntryActivity extends BaseActivity {


    private static final Uri bookUri = Uri.parse("content://com.android.devartexplore.contentprovier/book");

    @BindView(R.id.txt_info)
    TextView txt_info;


    @BindView(R.id.btn_launch)
    Button btn_launch;

    @OnClick(R.id.btn_launch)
    public void onLaunchClick() {

        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", 4);
        contentValues.put("name", "NOKIA");

        getContentResolver().insert(bookUri, contentValues);

        queryBooks();
    }

    private void queryBooks() {
        Cursor cursor = getContentResolver
                ().query(bookUri, new String[]{"_id", "name"}, null, null, null);

        while (cursor.moveToNext()) {
            Book book = new Book();
            book.bookId = cursor.getInt(0);
            book.bookName = cursor.getString(1);

            LogUtils.d(book.bookId + ":" + book.bookName);
        }
    }

    @Override
    protected int proviceContentId() {
        return R.layout.activity_info_with_button;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private ContentObserver contentObserver = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btn_launch.setText("添加");

        queryBooks();

        contentObserver = new ContentObserver(handler) {
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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getContentResolver().unregisterContentObserver(contentObserver);
    }
}
