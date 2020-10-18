package com.example.duanmaulab1.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmaulab1.R;

public class Top10Holder extends RecyclerView.ViewHolder {

    public TextView tvTenSachBanChay;
    public TextView tvSoLuongSachBanChay;

    public Top10Holder(@NonNull View itemView) {
        super(itemView);
        tvTenSachBanChay = itemView.findViewById(R.id.tvTenSachBanChay);
        tvSoLuongSachBanChay = itemView.findViewById(R.id.tvSoLuongSachBanChay);
    }
}
