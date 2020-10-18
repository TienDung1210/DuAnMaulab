package com.example.duanmaulab1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmaulab1.R;
import com.example.duanmaulab1.acitivity.AddBillActivity;
import com.example.duanmaulab1.dao.BillDAO;
import com.example.duanmaulab1.dao.BookDAO;
import com.example.duanmaulab1.holder.BillHoder;
import com.example.duanmaulab1.model.Bill;
import com.example.duanmaulab1.model.Book;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillHoder> {
    public Context context;
    public List<Bill> billList;
    public RecyclerView recyclerView;

    public BillAdapter(Context context, List<Bill> billList, RecyclerView recyclerView) {
        this.context = context;
        this.billList = billList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public BillHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(context).
                        inflate(R.layout.rowbill, parent, false);

        BillHoder billHoder = new BillHoder(view);

        return billHoder;


    }

    @Override
    public void onBindViewHolder(@NonNull final BillHoder billHoder, final int position) {
        final Bill bill = billList.get(position);
        billHoder.tvTenSach.setText(bill.getTenSachBill());
        billHoder.tvSoLuong.setText(bill.getSoLuongBill() + "");
        billHoder.tvGia.setText(bill.getGiaTien() + "");

        billHoder.ImgDeleteBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.Delete);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        billList.remove(position);
                        // xóa chế độ xem tại vị trí đã chọn
                        recyclerView.removeViewAt(position);
                        // cập nhật dữ liệu khi 1 vị trí bị gỡ bỏ
                        notifyItemRemoved(position);
                        // cập nhật số lượng phần tử khi tính từ vị trí thay đổi
                        notifyItemRangeChanged(position, billList.size());
                        // refresh(F5) để cập nhật lại dữ liệu trong list
                        notifyDataSetChanged();
                        AddBillActivity.SetTongTien();
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return billList.size();
    }

}

