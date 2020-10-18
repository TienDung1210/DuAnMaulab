package com.example.duanmaulab1.model;

import java.util.Date;

public class Bill {
    public int MaHD;
    public java.util.Date Date;
    public String  TenSachBill;
    public double SoLuongBill;
    public double GiaTien;
    public double TongTien;

    public Bill() {
    }

    public Bill(Date date, String tenSachBill, double soLuongBill, double giaTien) {
        Date = date;
        TenSachBill = tenSachBill;
        SoLuongBill = soLuongBill;
        GiaTien = giaTien;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getTenSachBill() {
        return TenSachBill;
    }

    public void setTenSachBill(String tenSachBill) {
        TenSachBill = tenSachBill;
    }

    public double getSoLuongBill() {
        return SoLuongBill;
    }

    public void setSoLuongBill(double soLuongBill) {
        SoLuongBill = soLuongBill;
    }

    public double getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(double giaTien) {
        GiaTien = giaTien;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double tongTien) {
        TongTien = tongTien;
    }
}
