package com.example.duanmaulab1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmaulab1.dao.BillDAO;

public class BillOpenHelper extends SQLiteOpenHelper {
    public static final String DB_BILL = "HOADON";
    public static final int VERSION = 1;

    public BillOpenHelper(Context context) {
        super(context, DB_BILL, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BillDAO.SQL_NEWTABLE_BILL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + BillDAO.TABLE_NAME_HOADON);//xoa bang neu da ton tai
    }
}
