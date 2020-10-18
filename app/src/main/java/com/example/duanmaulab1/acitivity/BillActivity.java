package com.example.duanmaulab1.acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.duanmaulab1.R;
import com.example.duanmaulab1.adapter.BillAdapter;
import com.example.duanmaulab1.adapter.DetailBillAdapter;
import com.example.duanmaulab1.dao.BillDAO;
import com.example.duanmaulab1.model.Bill;

import java.text.ParseException;
import java.util.List;

public class BillActivity extends AppCompatActivity {

    List<Bill> billList;
    RecyclerView recyclerView;
    DetailBillAdapter detailBillAdapter;
    BillDAO billDAO;
    TextView tvTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        setTitle("Hóa Đơn");
        recyclerView = findViewById(R.id.RclBill);
        tvTongTien = findViewById(R.id.tvTongTienALLHD);
        billDAO = new BillDAO(this);
        try {
            billList = billDAO.ALLBILL();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailBillAdapter = new DetailBillAdapter(this, billList, recyclerView);
        recyclerView.setAdapter(detailBillAdapter);

        Double TongTien = billDAO.showTongHD();
        tvTongTien.setText(TongTien + " " + "VNĐ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //truyen file xml
        getMenuInflater().inflate(R.menu.category, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                System.exit(0);
                break;

            case R.id.ITcategory:
                Intent intent = new Intent(BillActivity.this, AddBillActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
