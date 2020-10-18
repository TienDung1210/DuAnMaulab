package com.example.duanmaulab1.model;

public class Top10 {
    public String TenSach;
    public double SoLuong;

    public Top10() {
    }

    public Top10(String tenSach, double soLuong) {
        TenSach = tenSach;
        SoLuong = soLuong;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public double getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(double soLuong) {
        SoLuong = soLuong;
    }
}
