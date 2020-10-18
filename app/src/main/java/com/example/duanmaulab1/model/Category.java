package com.example.duanmaulab1.model;

public class Category {
    public int MaTL;
    public String TenTL;
    public int ViTri;
    public String MoTa;


    public Category() {
    }




    public Category(String tenTL, int viTri, String moTa) {
        TenTL = tenTL;
        ViTri = viTri;
        MoTa = moTa;
    }

    public int getMaTL() {
        return MaTL;
    }

    public void setMaTL(int maTL) {
        MaTL = maTL;
    }

    public String getTenTL() {
        return TenTL;
    }

    public void setTenTL(String tenTL) {
        TenTL = tenTL;
    }

    public int getViTri() {
        return ViTri;
    }

    public void setViTri(int viTri) {
        ViTri = viTri;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }
}
