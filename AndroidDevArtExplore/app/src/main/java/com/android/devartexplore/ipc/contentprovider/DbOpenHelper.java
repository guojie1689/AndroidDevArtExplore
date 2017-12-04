package com.android.devartexplore.ipc.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gj on 2017/12/3.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String db_name = "book_provider.db";
    public static final String TABLE_NAME_BOOK = "tab_book";
    public static final String TABLE_NAME_USER = "tab_user";

    private static String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_BOOK + " (_id INTEGER PRIMARY KEY, name TEXT)";
    private static String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USER + " (_id INTEGER PRIMARY KEY, name TEXT,sex INT)";

    public DbOpenHelper(Context context) {
        super(context, db_name, null, 1);
    }

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
