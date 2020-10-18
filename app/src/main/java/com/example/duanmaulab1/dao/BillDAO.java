package com.example.duanmaulab1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmaulab1.database.BillOpenHelper;
import com.example.duanmaulab1.model.Bill;
import com.example.duanmaulab1.model.Top10;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    BillOpenHelper billOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    public static final String SQL_NEWTABLE_BILL = "CREATE TABLE BILL (" +
            "MAHD INTEGER primary key AUTOINCREMENT," + " DATE date ," + " TENSACHBILL text," + " SOLUONGBILL double," + "TIEN double," + " TONGTIEN double)";

    public static final String TABLE_NAME_HOADON = "BILL";
    public static final String tc_MAHD = "MAHD";
    public static final String tc_DATE = "DATE";
    public static final String tc_TENSACHBILL = "TENSACHBILL";
    public static final String tc_SOLUONGBILL = "SOLUONGBILL";
    public static final String tc_MONEYBILL = "TIEN";
    public static final String tc_SUMMONEYBILL = "TONGTIEN";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public BillDAO(Context context) {
        billOpenHelper = new BillOpenHelper(context);//tao DB
        sqLiteDatabase = billOpenHelper.getWritableDatabase();//cho phep ghi
    }

    public int InsertBill(Bill bill) {
        ContentValues values = new ContentValues();
        values.put(tc_DATE, simpleDateFormat.format(bill.getDate()));
        values.put(tc_TENSACHBILL, bill.getTenSachBill());
        values.put(tc_SOLUONGBILL, bill.getSoLuongBill() + "");
        values.put(tc_MONEYBILL, bill.getGiaTien() + "");

        if (sqLiteDatabase.insert(TABLE_NAME_HOADON, null, values) < 0) {

            return -1; //insert khong thanh cong
        }
        return 1; //insert thanh cong
    }


    public double showTongHD() {

        double tongTien = 0;
        SQLiteDatabase sqLiteDatabase = billOpenHelper.getReadableDatabase();

        String select1 = " SELECT " + " SUM( " + tc_SOLUONGBILL + " * " + tc_MONEYBILL + " )AS " + tc_SUMMONEYBILL
                + " FROM " + TABLE_NAME_HOADON;

        Cursor cursor = sqLiteDatabase.rawQuery(select1, null);
        if (cursor.moveToFirst()) {
            do {
                tongTien = cursor.getDouble(cursor.getColumnIndex(tc_SUMMONEYBILL));
            } while (cursor.moveToNext());


        }
        // dong ket noi con tro
        cursor.close();
        // dong ket noi DB
        sqLiteDatabase.close();

        return tongTien;
    }


    public List<Bill> ALLBILL() throws ParseException {
        List<Bill> list = new ArrayList<Bill>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_HOADON, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Bill bill = new Bill();
            bill.setMaHD(cursor.getInt(cursor.getColumnIndex(tc_MAHD)));
            bill.setDate(simpleDateFormat.parse(cursor.getString(cursor.getColumnIndex(tc_DATE))));
            bill.setTenSachBill(cursor.getString(cursor.getColumnIndex(tc_TENSACHBILL)));
            bill.setSoLuongBill(cursor.getDouble(cursor.getColumnIndex(tc_SOLUONGBILL)));
            bill.setGiaTien(cursor.getDouble(cursor.getColumnIndex(tc_MONEYBILL)));
            bill.setTongTien(cursor.getDouble(cursor.getColumnIndex(tc_SUMMONEYBILL)));
            list.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<Top10> showTop10() {
        List<Top10> top10SachBanChayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = billOpenHelper.getReadableDatabase();

        String select2 = "SELECT " + tc_TENSACHBILL + "," + " SUM(" + tc_SOLUONGBILL + " )AS " + tc_SOLUONGBILL
                + " FROM " + TABLE_NAME_HOADON
                + " GROUP BY " + tc_TENSACHBILL
                + " ORDER BY " + tc_SOLUONGBILL
                + " DESC LIMIT 10 ";

        Cursor cursor = sqLiteDatabase.rawQuery(select2, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Top10 top10SachBanChay = new Top10();
            top10SachBanChay.setTenSach(cursor.getString(cursor.getColumnIndex(tc_TENSACHBILL)));
            top10SachBanChay.setSoLuong(cursor.getDouble(cursor.getColumnIndex(tc_SOLUONGBILL)));
            top10SachBanChayList.add(top10SachBanChay);
            cursor.moveToNext();
        }
        // dong ket noi con tro
        cursor.close();
        // dong ket noi DB
        sqLiteDatabase.close();
        return top10SachBanChayList;
    }

    public double showHoaDonTheoNgay() {

        double tongTienHomNay = 0;
        SQLiteDatabase sqLiteDatabase = billOpenHelper.getReadableDatabase();

        String select3 = " SELECT " + " SUM( " + tc_SOLUONGBILL + " * " + tc_MONEYBILL + " )AS " + tc_SUMMONEYBILL + "," + " date('now') "
                + " FROM " + TABLE_NAME_HOADON
                + " GROUP BY " + tc_DATE;


        Cursor cursor = sqLiteDatabase.rawQuery(select3, null);
        if (cursor.moveToFirst()) {
            do {
                tongTienHomNay = cursor.getDouble(cursor.getColumnIndex(tc_SUMMONEYBILL));
            } while (cursor.moveToNext());
        }
        // dong ket noi con tro
        cursor.close();
        // dong ket noi DB
        sqLiteDatabase.close();

        return tongTienHomNay;
    }

    public double showHoaDonTheoThang() {

        double tongTienThangNay = 0;
        SQLiteDatabase sqLiteDatabase = billOpenHelper.getReadableDatabase();

        String select3 = " SELECT " + " SUM( " + tc_SOLUONGBILL + " * " + tc_MONEYBILL + " )AS " + tc_SUMMONEYBILL + "," + " strftime('%m','now') "
                + " FROM " + TABLE_NAME_HOADON
                + " GROUP BY " + tc_DATE;

        Cursor cursor = sqLiteDatabase.rawQuery(select3, null);
        if (cursor.moveToFirst()) {
            do {
                tongTienThangNay = cursor.getDouble(cursor.getColumnIndex(tc_SUMMONEYBILL));
            } while (cursor.moveToNext());
        }
        // dong ket noi con tro
        cursor.close();
        // dong ket noi DB
        sqLiteDatabase.close();

        return tongTienThangNay;
    }

    public double showHoaDonTheoNam() {

        double tongTienNamNay = 0;
        SQLiteDatabase sqLiteDatabase = billOpenHelper.getReadableDatabase();

        String select3 = " SELECT " + " SUM( " + tc_SOLUONGBILL + " * " + tc_MONEYBILL + " )AS " + tc_SUMMONEYBILL + "," + " strftime('%y','now') "
                + " FROM " + TABLE_NAME_HOADON
                + " GROUP BY " + tc_DATE;


        Cursor cursor = sqLiteDatabase.rawQuery(select3, null);
        if (cursor.moveToFirst()) {
            do {
                tongTienNamNay = cursor.getDouble(cursor.getColumnIndex(tc_SUMMONEYBILL));
            } while (cursor.moveToNext());
        }
        // dong ket noi con tro
        cursor.close();
        // dong ket noi DB
        sqLiteDatabase.close();

        return tongTienNamNay;
    }

}
