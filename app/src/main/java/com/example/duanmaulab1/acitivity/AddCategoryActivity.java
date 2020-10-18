package com.example.duanmaulab1.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmaulab1.dao.CategoryDAO;
import com.example.duanmaulab1.model.Category;
import com.example.duanmaulab1.R;

import java.util.ArrayList;
import java.util.List;

public class AddCategoryActivity extends AppCompatActivity {

    EditText edtTenTL, edtVitri, edtMoTa;
    List<Category> categoryList = new ArrayList<>();
    CategoryDAO categoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        categoryDao = new CategoryDAO(this);
        setTitle("Thêm Thể Loại Sách");
        Unit();
    }

    public void btnThemtheLoai(View view) {
        String TenTL = edtTenTL.getText().toString();
        int ViTri = Integer.parseInt(edtVitri.getText().toString());
        String MoTa = edtMoTa.getText().toString();
        if (TenTL.length() == 0 || String.valueOf(ViTri).length() == 0 || MoTa.length() == 0) {
            Toast.makeText(this, "Bạn Chưa Nhập TênTL hoặc Vị Trí hoặc Mô Tả", Toast.LENGTH_LONG).show();

        } else if (ViTri < 0) {
            Toast.makeText(this, "Vị trí lớn hơn 0 !", Toast.LENGTH_LONG).show();
        } else {
            Category category = new Category(TenTL, ViTri, MoTa);
            categoryList.add(category);
            if (categoryDao.InsertCategory(category) < 0) {
                Toast.makeText(getApplicationContext(), "Them that bai", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Them Thanh cong", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void btnShow(View view) {
        Intent intent = new Intent(AddCategoryActivity.this, CategoryActivity.class);
        startActivity(intent);
    }

    public void btnHủy(View view) {
        Intent intent = new Intent(AddCategoryActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void Unit() {
        edtTenTL = findViewById(R.id.edtTenTL);
        edtVitri = findViewById(R.id.edtVitri);
        edtMoTa = findViewById(R.id.edtMota);
    }
}
