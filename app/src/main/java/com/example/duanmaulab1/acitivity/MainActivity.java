package com.example.duanmaulab1.acitivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmaulab1.R;
import com.example.duanmaulab1.dao.BillDAO;

public class MainActivity extends AppCompatActivity {
    BillDAO billDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Menu");
    }

    public void ImgPeopleuser(View view) {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        startActivity(intent);
    }

    public void ImgTheloai(View view) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(intent);
    }


    public void ImgBook(View view) {
        Intent intent = new Intent(MainActivity.this, BookActivity.class);
        startActivity(intent);
    }

    public void ImgHoaDon(View view) {
        Intent intent = new Intent(MainActivity.this, BillActivity.class);
        startActivity(intent);
    }

    public void ImgTop10(View view) {
        Intent intent = new Intent(MainActivity.this, Top10BookActivity.class);
        startActivity(intent);
    }

    public void ImgThongKe(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View dialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.statistical, null);
        builder.setView(dialog);
        TextView tvThongkeTheoNgay = dialog.findViewById(R.id.tvThongKeTheoNgay);
        TextView tvThongkeTheoThang = dialog.findViewById(R.id.tvThongKeTheoThang);
        TextView tvThongkeTheoNam = dialog.findViewById(R.id.tvThongKeTheoNam);

        billDAO = new BillDAO(MainActivity.this);
        tvThongkeTheoNgay.setText(billDAO.showHoaDonTheoNgay()+"");
        tvThongkeTheoThang.setText(billDAO.showHoaDonTheoThang()+"");
        tvThongkeTheoNam.setText(billDAO.showHoaDonTheoNam()+"");
        builder.create();
        builder.show();
    }
}
