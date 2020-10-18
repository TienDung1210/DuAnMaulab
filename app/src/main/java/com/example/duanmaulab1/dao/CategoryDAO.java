package com.example.duanmaulab1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmaulab1.model.Category;
import com.example.duanmaulab1.database.CategoryOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    CategoryOpenHelper categoryOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    public static final String SQL_NEWTABLE_CATEGORY = "CREATE TABLE THELOAI (" +
            "MATL INTEGER primary key AUTOINCREMENT," + " TENTL text ," + " VITRI INTEGER," + " MOTA text)";

    public static final String TABLE_NAME_THELOAI = "THELOAI";
    public static final String tc_MATL = "MATL";
    public static final String tc_TENTL = "TENTL";
    public static final String tc_VITRI = "VITRI";
    public static final String tc_MOTA = "MOTA";


    public CategoryDAO(Context context) {
        categoryOpenHelper = new CategoryOpenHelper(context);//tao DB
        sqLiteDatabase = categoryOpenHelper.getWritableDatabase();//cho phep ghi
    }

    public int InsertCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put(tc_TENTL, category.getTenTL());
        values.put(tc_VITRI, category.getViTri() + "");
        values.put(tc_MOTA, category.getMoTa());

        if (sqLiteDatabase.insert(TABLE_NAME_THELOAI, null, values) < 0) {

            return -1; //insert khong thanh cong
        }
        return 1; //insert thanh cong
    }

    public int UpdateCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put(tc_TENTL, category.getTenTL());
        values.put(tc_VITRI, category.getViTri() + "");
        values.put(tc_MOTA, category.getMoTa());

        if (sqLiteDatabase.update(TABLE_NAME_THELOAI, values, tc_MATL + "=?", new String[]{category.getMaTL() + ""}) < 0) {

            return -1; //insert khong thanh cong
        }
        return 1; //insert thanh cong
    }


    public List<Category> ALLCategory() {
        List<Category> list = new ArrayList<Category>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_THELOAI, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Category category = new Category();
            category.setMaTL(cursor.getInt(cursor.getColumnIndex(tc_MATL)));
            category.setTenTL(cursor.getString(cursor.getColumnIndex(tc_TENTL)));
            category.setViTri(cursor.getInt(cursor.getColumnIndex(tc_VITRI)));
            category.setMoTa(cursor.getString(cursor.getColumnIndex(tc_MOTA)));

            list.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int delete(int MaTL) {
        SQLiteDatabase sqLiteDatabase = categoryOpenHelper.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME_THELOAI, tc_MATL + "=?", new String[]{MaTL + ""});

    }
}
