package com.example.duanmaulab1.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmaulab1.R;

public class CategoryHolder extends RecyclerView.ViewHolder {
    public TextView tvViTri;

    public TextView tvTenTL;

    public ImageView imgDelete;

    public CategoryHolder(@NonNull View itemView) {
        super(itemView);
        tvViTri = itemView.findViewById(R.id.TvViTri);
        tvTenTL = itemView.findViewById(R.id.TvTenTL);
        imgDelete = itemView.findViewById(R.id.ImgDelete);
    }
}
