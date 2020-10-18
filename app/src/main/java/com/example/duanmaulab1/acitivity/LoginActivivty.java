package com.example.duanmaulab1.acitivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmaulab1.R;
import com.example.duanmaulab1.dao.UserDAO;
import com.example.duanmaulab1.model.User;

public class LoginActivivty extends AppCompatActivity {
    public EditText edtuser;
    public EditText edtpass;
    UserDAO userDAO;
    public CheckBox chkcheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);
        chkcheckbox = findViewById(R.id.chkcheckbox);
        setTitle("Login");
        ShowChecked();
    }

    public void btnLogin(View view) {
        isCheked();
        userDAO = new UserDAO(this);
        String username = edtuser.getText().toString().trim();
        String password = edtpass.getText().toString().trim();
        User user = new User(username, password);
        boolean result = userDAO.isLogin(user);
        if (result) {
            Intent intent = new Intent(LoginActivivty.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_LONG).show();
        } else if (username.equals("fpt") && password.equals("fpt")) {
            Intent intent = new Intent(LoginActivivty.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_LONG).show();
        } else if (username.equals("") || password.equals("")) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập tên đăng nhập hoặc mật khẩu", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "login failed", Toast.LENGTH_LONG).show();
        }

    }

    public void isCheked() {
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARE.f", Context.MODE_PRIVATE);
        // đưa dữ liệu vào = editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String username = edtuser.getText().toString();
        String password = edtpass.getText().toString();
        boolean result = chkcheckbox.isChecked();
        if (!result) {
            editor.putString("user", username);
            editor.putString("pass", password);
            editor.putBoolean("save", result);
        } else {
            editor.putString("user", username);
            editor.putString("pass", password);
            editor.putBoolean("save", result);
        }
        // lưu xuống ổ C
        editor.commit();
    }

    public void ShowChecked() {
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARE.f", Context.MODE_PRIVATE);
        boolean result = sharedPreferences.getBoolean("save", false);
        if (result) {
            String user = sharedPreferences.getString("user", null);
            String pass = sharedPreferences.getString("pass", null);
            edtuser.setText(user);
            edtpass.setText(pass);
        } else {
            String user = sharedPreferences.getString("user", null);
            edtuser.setText(user);
        }

        chkcheckbox.setChecked(result);
    }

}
