package com.example.duanmaulab1.acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.duanmaulab1.R;
import com.example.duanmaulab1.adapter.DetailBillAdapter;
import com.example.duanmaulab1.adapter.Top10Adapter;
import com.example.duanmaulab1.dao.BillDAO;
import com.example.duanmaulab1.model.Bill;
import com.example.duanmaulab1.model.Top10;

import java.util.List;

public class Top10BookActivity extends AppCompatActivity {
    List<Top10> Top10List;
    RecyclerView recyclerView;
    Top10Adapter top10Adapter;
    BillDAO billDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10_book);
        recyclerView = findViewById(R.id.rclTop10);
        setTitle("Top 10 Sách Bán Chạy");
        billDAO = new BillDAO(this);
        Top10List = billDAO.showTop10();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        top10Adapter = new Top10Adapter(this, Top10List, recyclerView);
        recyclerView.setAdapter(top10Adapter);

    }


}
