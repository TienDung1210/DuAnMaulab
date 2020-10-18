package com.example.duanmaulab1.acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.duanmaulab1.adapter.BookAdapter;
import com.example.duanmaulab1.dao.BookDAO;
import com.example.duanmaulab1.model.Book;
import com.example.duanmaulab1.R;
import com.example.duanmaulab1.model.Category;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    List<Book> bookList = new ArrayList<>();
    BookDAO bookDao;
    RecyclerView recyclerView;
    BookAdapter bookAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        recyclerView = findViewById(R.id.RclBook);
        setTitle("Sách");
        Unit();
    }

    public void Unit() {
        bookDao = new BookDAO(this);
        bookList = bookDao.ALLBOOK();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(this, bookList, recyclerView);
        recyclerView.setAdapter(bookAdapter);
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
                Intent intent = new Intent(BookActivity.this, AddBookActivivy.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
