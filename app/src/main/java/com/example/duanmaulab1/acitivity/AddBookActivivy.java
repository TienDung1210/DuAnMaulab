package com.example.duanmaulab1.acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmaulab1.adapter.CategorySpinnerAdapter;
import com.example.duanmaulab1.dao.BookDAO;
import com.example.duanmaulab1.dao.CategoryDAO;
import com.example.duanmaulab1.model.Book;
import com.example.duanmaulab1.R;
import com.example.duanmaulab1.model.Category;

import java.util.ArrayList;
import java.util.List;

public class AddBookActivivy extends AppCompatActivity {

    EditText edtTensach, edtTacGia, edtNhaXB, edtGiaBia, edtSL;
    List<Book> bookList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    BookDAO bookDao;
    Spinner spinnerTenTL;
    CategoryDAO categoryDAO;
    String TenTL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        setTitle("Thêm Sách");
        bookDao = new BookDAO(this);
        categoryDAO = new CategoryDAO(this);
        categoryList = categoryDAO.ALLCategory();
        Unit();

        CategorySpinnerAdapter categorySpinnerAdapter = new CategorySpinnerAdapter(AddBookActivivy.this, categoryList);
        spinnerTenTL.setAdapter(categorySpinnerAdapter);

        spinnerTenTL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TenTL = categoryList.get(spinnerTenTL.getSelectedItemPosition()).getTenTL();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Ban chua chon Ten The Loai", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void btnThemSach(View view) {
        String TenSach = edtTensach.getText().toString();
        String TacGia = edtTacGia.getText().toString();
        String NhaXB = edtNhaXB.getText().toString();
        double GiaBia = Double.parseDouble(edtGiaBia.getText().toString());
        double SL = Double.parseDouble(edtSL.getText().toString());

        if (TenSach.length() == 0 || TacGia.length() == 0 || NhaXB.length() == 0 || String.valueOf(GiaBia).length() == 0 || String.valueOf(SL).length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn Nhập Thiếu Dữ Liệu, Vui Lòng Thử Lại !", Toast.LENGTH_LONG).show();
        }
        else if (GiaBia<0 || SL <0){
            Toast.makeText(getApplicationContext(), "Giá Bìa và Số Lượng phải lớn hơn 0", Toast.LENGTH_LONG).show();
        }
        else {
            Book book = new Book(TenSach, TacGia, NhaXB, GiaBia, SL, TenTL);
            bookList.add(book);
            if (bookDao.InsertBook(book) < 0) {
                Toast.makeText(getApplicationContext(), "Them that bai", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Them Thanh cong", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void btnShow(View view) {
        Intent intent = new Intent(AddBookActivivy.this, BookActivity.class);
        startActivity(intent);
    }

    public void btnHủy(View view) {
        Intent intent = new Intent(AddBookActivivy.this, Book.class);
        startActivity(intent);
    }

    public void Unit() {
        edtTensach = findViewById(R.id.edtTenSach);
        edtTacGia = findViewById(R.id.edtTacgia);
        edtNhaXB = findViewById(R.id.edtNhaXB);
        edtGiaBia = findViewById(R.id.edtGiaBia);
        edtSL = findViewById(R.id.edtSL);
        spinnerTenTL = findViewById(R.id.spTenTL);
    }

    public void btnThemTL(View view) {
        Intent intent = new Intent(AddBookActivivy.this, AddCategoryActivity.class);
        startActivity(intent);
    }

}
