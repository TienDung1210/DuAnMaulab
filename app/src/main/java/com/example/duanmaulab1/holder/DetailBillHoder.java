package com.example.duanmaulab1.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmaulab1.R;

public class DetailBillHoder extends RecyclerView.ViewHolder {

    public TextView tvDate,tvMaHD;

    public DetailBillHoder(@NonNull View itemView) {
        super(itemView);
        tvDate = itemView.findViewById(R.id.tvDetailDate);
        tvMaHD = itemView.findViewById(R.id.tvMaHD);
    }
}
