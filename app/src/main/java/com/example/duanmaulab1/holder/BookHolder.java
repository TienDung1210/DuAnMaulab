package com.example.duanmaulab1.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmaulab1.R;

public class BookHolder extends RecyclerView.ViewHolder {
    public TextView tvTenSach;

    public TextView tvGia;

    public ImageView imgDeleteBook;

    public BookHolder(@NonNull View itemView) {
        super(itemView);
        tvTenSach = itemView.findViewById(R.id.tvTenSach);
        tvGia = itemView.findViewById(R.id.TvGia);
        imgDeleteBook = itemView.findViewById(R.id.ImgDeleteBook);
    }
}
