package com.example.duanmaulab1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmaulab1.dao.CategoryDAO;

public class CategoryOpenHelper extends SQLiteOpenHelper {
    public static final String DB_CATEGORY = "THELOAI";
    public static final int VERSION = 1;

    public CategoryOpenHelper(Context context) {
        super(context, DB_CATEGORY, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoryDAO.SQL_NEWTABLE_CATEGORY);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + CategoryDAO.TABLE_NAME_THELOAI);//xoa bang neu da ton tai
    }

}
