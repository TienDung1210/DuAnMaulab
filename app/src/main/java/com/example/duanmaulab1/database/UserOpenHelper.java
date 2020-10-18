package com.example.duanmaulab1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmaulab1.dao.UserDAO;

public class UserOpenHelper extends SQLiteOpenHelper {

    public static final String DB_USER = "USER";
    public static final int VERSION = 1;

    public UserOpenHelper(Context context) {
        super(context, DB_USER, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDAO.SQL_NEWTABLE_USER);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + UserDAO.TABLE_NAME_USER);//xoa bang neu da ton tai
    }
}
