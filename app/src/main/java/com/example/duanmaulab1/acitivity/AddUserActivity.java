package com.example.duanmaulab1.acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmaulab1.dao.UserDAO;
import com.example.duanmaulab1.model.User;
import com.example.duanmaulab1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddUserActivity extends AppCompatActivity {

    EditText edtNameLogin, edtPassword, edtRetypePass, edtPhone, edtFullname;
    List<User> userList = new ArrayList<>();
    UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setTitle("Thêm Người Dùng");
        userDao = new UserDAO(this);
        Unit();
    }

    public void btnSave(View view) {
        String NameLogin = edtNameLogin.getText().toString();
        String Password = edtPassword.getText().toString();
        String RetypePass = edtRetypePass.getText().toString();
        String Phone = edtPhone.getText().toString();
        String FullName = edtFullname.getText().toString();

        if (NameLogin.length() == 0) {
            Toast.makeText(this, "bạn chưa nhập tên đăng nhập", Toast.LENGTH_LONG).show();
        } else if (Password.length() == 0 || Password.length() < 6) {
            Toast.makeText(this, "bạn chưa nhập pass hoặc pass phải >=6", Toast.LENGTH_LONG).show();
        } else if (RetypePass.length() == 0) {
            Toast.makeText(this, "bạn chưa nhập lại pass", Toast.LENGTH_LONG).show();

        } else if (!RetypePass.equals(Password)) {
            Toast.makeText(this, "pass phải trùng nhau", Toast.LENGTH_LONG).show();
        } else if (Phone.length() == 0 || Phone.length() < 10 || Phone.length() > 10) {
            Toast.makeText(this, "bạn chưa nhập số điện thoại hoặc phone = 10 số", Toast.LENGTH_LONG).show();
        } else if (FullName.length() == 0) {
            Toast.makeText(this, "bạn chưa nhập họ và tên", Toast.LENGTH_LONG).show();
        } else {
            User user = new User(NameLogin, Password, Phone, FullName);
            userList.add(user);
            if (userDao.InsertUser(user) < 0) {
                Toast.makeText(getApplicationContext(), "Them that bai", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Them Thanh cong", Toast.LENGTH_LONG).show();

            }
        }
    }


    public void btnCancel(View view) {
        Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void btnList(View view) {
        Intent intent = new Intent(AddUserActivity.this, UserActivity.class);
        startActivity(intent);
    }

    public void Unit() {
        edtNameLogin = findViewById(R.id.edtNameUser);
        edtPassword = findViewById(R.id.edtPassword);
        edtRetypePass = findViewById(R.id.edtTypePassword);
        edtPhone = findViewById(R.id.edtPhone);
        edtFullname = findViewById(R.id.edtFullname);


    }
}


