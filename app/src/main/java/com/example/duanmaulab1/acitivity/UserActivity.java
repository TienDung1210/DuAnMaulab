package com.example.duanmaulab1.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmaulab1.adapter.UserAdapter;
import com.example.duanmaulab1.dao.UserDAO;
import com.example.duanmaulab1.model.Bill;
import com.example.duanmaulab1.model.Book;
import com.example.duanmaulab1.model.Category;
import com.example.duanmaulab1.model.User;
import com.example.duanmaulab1.R;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    List<User> userList = new ArrayList<>();
    UserDAO userDao;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<Bill> billList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        recyclerView = findViewById(R.id.RclUser);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Người Dùng");
        Unit();
    }

    public void Unit() {
        userDao = new UserDAO(this);
        userList = userDao.ALLUser();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this, userList, recyclerView);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //truyen file xml
        getMenuInflater().inflate(R.menu.user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                System.exit(0);
                break;

            case R.id.menuthem:
                Intent intent = new Intent(UserActivity.this, AddUserActivity.class);
                startActivity(intent);
                break;


            case R.id.menudoipass:
                Intent intent1 = new Intent(UserActivity.this, ChangePassActivity.class);
                startActivity(intent1);
                break;

            case R.id.menuout:
                userList.clear();
                categoryList.clear();
                billList.clear();
                bookList.clear();
                Intent intent3 = new Intent(UserActivity.this, LoginActivivty.class);
                startActivity(intent3);
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
