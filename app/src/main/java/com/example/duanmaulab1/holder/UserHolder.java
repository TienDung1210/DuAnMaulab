package com.example.duanmaulab1.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmaulab1.R;

public class UserHolder extends RecyclerView.ViewHolder {
    public ImageView ImgDeleteUser;
    public TextView tvFullName;
    public TextView tvPhone;

    public UserHolder(@NonNull View itemView) {
        super(itemView);
        ImgDeleteUser = itemView.findViewById(R.id.ImgDeleteUser);
        tvFullName = itemView.findViewById(R.id.TvFullname);
        tvPhone = itemView.findViewById(R.id.TvPhone);

    }
}
