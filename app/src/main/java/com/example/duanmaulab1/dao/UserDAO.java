package com.example.duanmaulab1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmaulab1.model.User;
import com.example.duanmaulab1.database.UserOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    UserOpenHelper userOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    public static final String SQL_NEWTABLE_USER = "CREATE TABLE USER (" +
            " NAMELOGIN text primary key,"
            + " PASS text ,"
            + " PHONE text,"
            + " FULLNAME text) ";

    public static final String TABLE_NAME_USER = "USER";
    public static final String tc_NAMELOGIN = "NAMELOGIN";
    public static final String tc_PASS = "PASS";
    public static final String tc_PHONE = "PHONE";
    public static final String tc_FULLNAME = "FULLNAME";


    public UserDAO(Context context) {
        userOpenHelper = new UserOpenHelper(context);//tao DB
        sqLiteDatabase = userOpenHelper.getWritableDatabase();//cho phep ghi
    }

    public int InsertUser(User user) {
        ContentValues values = new ContentValues();
        // chèn 1 hàng mới
        values.put(tc_NAMELOGIN, user.getUserName());
        values.put(tc_PASS, user.getPassword());
        values.put(tc_PHONE, user.getPhoneNumber());
        values.put(tc_FULLNAME, user.getFullName());
        if (sqLiteDatabase.insert(TABLE_NAME_USER, null, values) < 0) {

            return -1; //insert khong thanh cong
        }
        return 1; //insert thanh cong
    }

    public int UpdateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(tc_PHONE, user.getPhoneNumber());
        values.put(tc_FULLNAME, user.getFullName());
        if (sqLiteDatabase.update(TABLE_NAME_USER, values, tc_NAMELOGIN + "=?", new String[]{user.getUserName()}) < 0) {

            return -1; // khong thanh cong
        }
        return 1; // thanh cong
    }

    public boolean isLogin(User user) {
        String select = " select " + tc_NAMELOGIN + "," + tc_PASS
                + " from " + TABLE_NAME_USER
                + " where " + tc_NAMELOGIN + "=?" + " and " + tc_PASS + "=?";

        String username = user.getUserName();
        String password = user.getPassword();

        Cursor c = sqLiteDatabase.rawQuery(select, new String[]{username, password});

        if (c.moveToFirst()) {
            return true;
        }
        return false;
    }

    public int changePasswordUser(User user) {
        ContentValues values = new ContentValues();
        values.put(tc_NAMELOGIN, user.getUserName());
        values.put(tc_PASS, user.getPassword());
        int result = sqLiteDatabase.update(TABLE_NAME_USER, values, tc_NAMELOGIN + "=?", new String[]{user.getUserName()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }


    public List<User> ALLUser() {
        List<User> list = new ArrayList<User>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_USER, null, null, null, null, null, null);
        // truy vấn hàng đầu tiên
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            User user = new User();
            user.setUserName(cursor.getString(cursor.getColumnIndex(tc_NAMELOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(tc_PASS)));
            user.setPhoneNumber(cursor.getString(cursor.getColumnIndex(tc_PHONE)));
            user.setFullName(cursor.getString(cursor.getColumnIndex(tc_FULLNAME)));
            list.add(user);
            // truy vấn hàng tiếp theo
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int delete(String user) {
        SQLiteDatabase sqLiteDatabase = userOpenHelper.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME_USER, tc_NAMELOGIN + "=?", new String[]{user});

    }
}
