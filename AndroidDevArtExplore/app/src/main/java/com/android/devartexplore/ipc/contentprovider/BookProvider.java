package com.android.devartexplore.ipc.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.android.devartexplore.ipc.aidl.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gj on 2017/12/3.
 */

public class BookProvider extends ContentProvider {

    private List<Book> bookList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();

    private static final String authorize = "com.android.devartexplore.contentprovier";
    private static final int CONTENT_ID_BOOK = 0;
    private static final int CONTENT_ID_USER = 1;

    private Context mContext = null;
    private SQLiteDatabase mdb = null;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(authorize, "book", 0);
        uriMatcher.addURI(authorize, "user", 1);
    }


    private void initBookList() {
        mdb = new DbOpenHelper(mContext).getWritableDatabase();

        mdb.execSQL("delete from " + DbOpenHelper.TABLE_NAME_BOOK);
        mdb.execSQL("delete from " + DbOpenHelper.TABLE_NAME_USER);

        mdb.execSQL("insert into " + DbOpenHelper.TABLE_NAME_BOOK + " values(1,'Android')");
        mdb.execSQL("insert into " + DbOpenHelper.TABLE_NAME_BOOK + " values(2,'IOS')");
        mdb.execSQL("insert into " + DbOpenHelper.TABLE_NAME_BOOK + " values(3,'Html5')");

        mdb.execSQL("insert into " + DbOpenHelper.TABLE_NAME_USER + " values(1,'jake',1)");
        mdb.execSQL("insert into " + DbOpenHelper.TABLE_NAME_USER + " values(2,'jasmine',0)");

    }


    @Override
    public boolean onCreate() {

        mContext = getContext();

        initBookList();

        return false;
    }

    private static String getTableName(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CONTENT_ID_BOOK:
                return DbOpenHelper.TABLE_NAME_BOOK;
            case CONTENT_ID_USER:
                return DbOpenHelper.TABLE_NAME_USER;
        }

        return "";
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        String tableName = getTableName(uri);

        return mdb.query(tableName, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = getTableName(uri);

        mdb.insert(tableName, null, values);

        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);

        int count = mdb.delete(tableName, null, selectionArgs);

        if (count > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }

        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);

        int count = mdb.update(tableName, values, selection, selectionArgs);
        if (count > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }

        return count;
    }
}
