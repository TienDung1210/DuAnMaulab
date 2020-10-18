package com.example.duanmaulab1.model;

public class Book {
    public int Masach;
    public String Tensach, TacGia, NhaXuatBan;
    public double GiaBia, SoLuong;
    public String TenTLBook;

    public Book() {
    }

    ;

    public Book(String tensach, String tacGia, String nhaXuatBan, double giaBia, double soLuong, String tenTLBook) {
        Tensach = tensach;
        TacGia = tacGia;
        NhaXuatBan = nhaXuatBan;
        GiaBia = giaBia;
        SoLuong = soLuong;
        TenTLBook = tenTLBook;
    }

    public int getMasach() {
        return Masach;
    }

    public void setMasach(int masach) {
        Masach = masach;
    }

    public String getTensach() {
        return Tensach;
    }

    public void setTensach(String tensach) {
        Tensach = tensach;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return NhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        NhaXuatBan = nhaXuatBan;
    }

    public double getGiaBia() {
        return GiaBia;
    }

    public void setGiaBia(double giaBia) {
        GiaBia = giaBia;
    }

    public double getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(double soLuong) {
        SoLuong = soLuong;
    }

    public String getTenTLBook() {
        return TenTLBook;
    }

    public void setTenTLBook(String tenTLBook) {
        TenTLBook = tenTLBook;
    }
}
