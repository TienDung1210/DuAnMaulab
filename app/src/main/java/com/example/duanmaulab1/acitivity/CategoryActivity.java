package com.example.duanmaulab1.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmaulab1.adapter.CategoryAdapter;
import com.example.duanmaulab1.dao.CategoryDAO;
import com.example.duanmaulab1.model.Category;
import com.example.duanmaulab1.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    List<Category> categoryList = new ArrayList<>();
    CategoryDAO categoryDao;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerView = findViewById(R.id.RclCategory);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Thể Loại Sách");
        Unit();
    }

    public void Unit() {
        categoryDao = new CategoryDAO(this);
        categoryList = categoryDao.ALLCategory();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter(this, categoryList, recyclerView);
        recyclerView.setAdapter(categoryAdapter);
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
                Intent intent = new Intent(CategoryActivity.this, AddCategoryActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
