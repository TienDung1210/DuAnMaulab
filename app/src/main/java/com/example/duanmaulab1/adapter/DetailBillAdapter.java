package com.example.duanmaulab1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmaulab1.R;
import com.example.duanmaulab1.dao.BillDAO;
import com.example.duanmaulab1.dao.CategoryDAO;
import com.example.duanmaulab1.holder.CategoryHolder;
import com.example.duanmaulab1.holder.DetailBillHoder;
import com.example.duanmaulab1.model.Bill;
import com.example.duanmaulab1.model.Category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DetailBillAdapter extends RecyclerView.Adapter<DetailBillHoder> {
    public Context context;
    public List<Bill> billList;
    public RecyclerView recyclerView;


    public DetailBillAdapter(Context context, List<Bill> billList, RecyclerView recyclerView) {

        this.context = context;
        this.billList = billList;
        this.recyclerView = recyclerView;
    }


    @NonNull
    @Override
    public DetailBillHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(context).
                        inflate(R.layout.rowdetailbill, parent, false);

        DetailBillHoder detailBillHoder = new DetailBillHoder(view);

        return detailBillHoder;


    }

    @Override
    public void onBindViewHolder(@NonNull final DetailBillHoder detailBillHoder, final int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final Bill bill = billList.get(position);
        detailBillHoder.tvDate.setText(simpleDateFormat.format(bill.getDate()));
        detailBillHoder.tvMaHD.setText(bill.getMaHD()+"");
        final BillDAO billDAO = new BillDAO(context);


        detailBillHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.Detail);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View dialog1 = LayoutInflater.from(context).inflate(R.layout.showdetailbill, null);
                        builder.setView(dialog1);
                        TextView tvDetailMaHD = dialog1.findViewById(R.id.tvDetailHDMaHoaDon);
                        TextView tvDetailTenSach = dialog1.findViewById(R.id.tvDetailHDTenSach);
                        TextView tvDetailSL = dialog1.findViewById(R.id.tvDetailHDSoLuong);
                        TextView tvDetailGiaTien = dialog1.findViewById(R.id.tvDetailHDGiaTien);
                        TextView tvDetailTongTien = dialog1.findViewById(R.id.tvDetailHDTongTien);

                        try {
                            billList = billDAO.ALLBILL();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        tvDetailMaHD.setText(bill.getMaHD() + "");
                        tvDetailTenSach.setText(bill.getTenSachBill());
                        tvDetailSL.setText(bill.getSoLuongBill() + "");
                        tvDetailGiaTien.setText(bill.getGiaTien() + "");
                        tvDetailTongTien.setText(bill.getSoLuongBill() * bill.getGiaTien() + "");

                        builder.create();
                        builder.show();
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

