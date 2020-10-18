package com.example.duanmaulab1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmaulab1.model.Book;
import com.example.duanmaulab1.database.BookOpenHelper;
import com.example.duanmaulab1.model.User;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    BookOpenHelper bookOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    public static final String SQL_NEWTABLE_BOOK = "CREATE TABLE BOOK (" +
            "MASACH INTEGER primary key AUTOINCREMENT," + " TENSACH text ," + " TACGIA text," + " NHAXB text," + " GIABIA double," + " SOLUONG double," + " TENTL text) ";

    public static final String TABLE_NAME_SACH = "BOOK";
    public static final String tc_MASACH = "MASACH";
    public static final String tc_TENSACH = "TENSACH";
    public static final String tc_TACGIA = "TACGIA";
    public static final String tc_NHAXB = "NHAXB";
    public static final String tc_GIABIA = "GIABIA";
    public static final String tc_SOLUONG = "SOLUONG";
    public static final String tc_TENTL = "TENTL";


    public BookDAO(Context context) {
        bookOpenHelper = new BookOpenHelper(context);//tao DB
        sqLiteDatabase = bookOpenHelper.getWritableDatabase();//cho phep ghi
    }

    public int InsertBook(Book book) {
        ContentValues values = new ContentValues();
        values.put(tc_TENSACH, book.getTensach());
        values.put(tc_TACGIA, book.getTacGia());
        values.put(tc_NHAXB, book.getNhaXuatBan());
        values.put(tc_GIABIA, book.getGiaBia() + "");
        values.put(tc_SOLUONG, book.getSoLuong() + "");
        values.put(tc_TENTL, book.getTenTLBook());

        if (sqLiteDatabase.insert(TABLE_NAME_SACH, null, values) < 0) {

            return -1; //insert khong thanh cong
        }
        return 1; //insert thanh cong
    }

    public int UpdateBook(Book book) {
        ContentValues values = new ContentValues();
        values.put(tc_TENSACH, book.getTensach());
        values.put(tc_TACGIA, book.getTacGia());
        values.put(tc_NHAXB, book.getNhaXuatBan());
        values.put(tc_GIABIA, book.getGiaBia() + "");
        values.put(tc_SOLUONG, book.getSoLuong() + "");
        values.put(tc_TENTL, book.getTenTLBook());


        if (sqLiteDatabase.update(TABLE_NAME_SACH, values, tc_MASACH + "=?", new String[]{book.getMasach() + ""}) < 0) {

            return -1; //insert khong thanh cong
        }
        return 1; //insert thanh cong
    }


    public List<Book> ALLBOOK() {
        List<Book> list = new ArrayList<Book>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_SACH, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Book book = new Book();
            book.setMasach(cursor.getInt(cursor.getColumnIndex(tc_MASACH)));
            book.setTensach(cursor.getString(cursor.getColumnIndex(tc_TENSACH)));
            book.setTacGia(cursor.getString(cursor.getColumnIndex(tc_TACGIA)));
            book.setNhaXuatBan(cursor.getString(cursor.getColumnIndex(tc_NHAXB)));
            book.setGiaBia(cursor.getDouble(cursor.getColumnIndex(tc_GIABIA)));
            book.setSoLuong(cursor.getDouble(cursor.getColumnIndex(tc_SOLUONG)));
            book.setTenTLBook(cursor.getString(cursor.getColumnIndex(tc_TENTL)));

            list.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int deleteBook(int MaSach) {
        SQLiteDatabase sqLiteDatabase = bookOpenHelper.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME_SACH, tc_MASACH + "=?", new String[]{MaSach + ""});

    }


}
