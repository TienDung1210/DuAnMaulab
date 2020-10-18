package com.example.duanmaulab1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmaulab1.R;
import com.example.duanmaulab1.holder.BillHoder;
import com.example.duanmaulab1.holder.Top10Holder;
import com.example.duanmaulab1.model.Bill;
import com.example.duanmaulab1.model.Top10;

import java.util.List;

public class Top10Adapter extends RecyclerView.Adapter<Top10Holder> {
    public Context context;
    public List<Top10> top10List;
    public RecyclerView recyclerView;

    public Top10Adapter(Context context, List<Top10> top10List, RecyclerView recyclerView) {
        this.context = context;
        this.top10List = top10List;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public Top10Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(context).
                        inflate(R.layout.rowtop10, parent, false);

        Top10Holder top10Holder = new Top10Holder(view);

        return top10Holder;


    }

    @Override
    public void onBindViewHolder(@NonNull final Top10Holder top10Holder, final int position) {

        final Top10 top10 = top10List.get(position);
        top10Holder.tvTenSachBanChay.setText(top10.TenSach);
        top10Holder.tvSoLuongSachBanChay.setText(top10.SoLuong + "");

    }


    @Override
    public int getItemCount() {
        return top10List.size();
    }


}

