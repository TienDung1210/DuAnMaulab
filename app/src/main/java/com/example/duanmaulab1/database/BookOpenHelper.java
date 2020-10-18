package com.example.duanmaulab1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmaulab1.dao.BookDAO;

public class BookOpenHelper extends SQLiteOpenHelper {
    public static final String DB_BOOK = "SACH";
    public static final int VERSION = 1;

    public BookOpenHelper(Context context) {
        super(context, DB_BOOK, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BookDAO.SQL_NEWTABLE_BOOK);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + BookDAO.TABLE_NAME_SACH);//xoa bang neu da ton tai
    }
}
