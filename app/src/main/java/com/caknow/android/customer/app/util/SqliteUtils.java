package com.caknow.android.customer.app.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wesson_wxy on 2016/12/28.
 */

public class SqliteUtils {
    private static volatile SqliteUtils instance;

    private DbHelper                    dbHelper;
    private SQLiteDatabase              db;

    private SqliteUtils(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static SqliteUtils getInstance(Context context) {
        if(instance == null) {
            synchronized (SqliteUtils.class) {
                if(instance == null) {
                    instance = new SqliteUtils(context);
                }
            }
        }
        return instance;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
