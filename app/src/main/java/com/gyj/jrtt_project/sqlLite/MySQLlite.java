package com.gyj.jrtt_project.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * data:2017/4/14
 * author:郭彦君(Administrator)
 * function:
 */
public class MySQLlite extends SQLiteOpenHelper {
    public MySQLlite(Context context) {
        super(context, "channel.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSql = "create table channel(_id integer primary key autoincrement ,name text, url text, style Text )";

        db.execSQL(createSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
