package com.gdy.dn_2019_10_27.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gdy.dn_2019_10_27.app.YApplication;
import com.gdy.dn_2019_10_27.news.table.NewsChannelTable;
import com.gdy.ytool.LogUtils;

/**
 * Created by gongdongyang
 * on 2019/12/13
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Toutiao";
    private static final int DB_VERSION = 6;
    private static final String CLEAR_TABLE_DATA = "delete from ";
    private static final String DROP_TABLE = "drop table if exists ";
    private static DatabaseHelper instance = null;
    private static SQLiteDatabase db = null;

    private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static synchronized DatabaseHelper getInstance() {
        LogUtils.d("DatabaseHelper","===getInstance===");
        if (instance == null) {
            instance = new DatabaseHelper(YApplication.getInstance(), DB_NAME, null, DB_VERSION);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LogUtils.d("DatabaseHelper","===onCreate===");
        db.execSQL(NewsChannelTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static synchronized SQLiteDatabase getDatabase() {
        if (db == null) {
            db = getInstance().getWritableDatabase();
        }
        return db;
    }
}
