package com.example.duanmaulab1.acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmaulab1.R;
import com.example.duanmaulab1.dao.UserDAO;
import com.example.duanmaulab1.model.User;

public class ChangePassActivity extends AppCompatActivity {
    UserDAO userDAO;
    EditText edtChangePass, edtTypeChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        edtChangePass = findViewById(R.id.edtChangePassword);
        edtTypeChangePass = findViewById(R.id.edtChangeTypePassword);
    }

    public void btnSavechangepass(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARE.f", Context.MODE_PRIVATE);
        String UserName = sharedPreferences.getString("user", "");
        userDAO = new UserDAO(ChangePassActivity.this);
        String ChangePass = edtChangePass.getText().toString();
        User user = new User(UserName, ChangePass, "", "");
        if (userDAO.changePasswordUser(user) > 0) {
            Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnCancelchangepass(View view) {
        Intent intent = new Intent(ChangePassActivity.this, UserActivity.class);
        startActivity(intent);
    }
}


