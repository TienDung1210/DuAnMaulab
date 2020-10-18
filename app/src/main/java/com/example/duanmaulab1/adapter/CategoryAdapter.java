package com.example.duanmaulab1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.duanmaulab1.acitivity.AddBookActivivy;
import com.example.duanmaulab1.dao.CategoryDAO;
import com.example.duanmaulab1.holder.CategoryHolder;
import com.example.duanmaulab1.model.Category;
import com.example.duanmaulab1.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {
    public Context context;
    public List<Category> categoryList;
    public RecyclerView recyclerView;


    public CategoryAdapter(Context context, List<Category> categoryList, RecyclerView recyclerView) {

        this.context = context;
        this.categoryList = categoryList;
        this.recyclerView = recyclerView;
    }


    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(context).
                        inflate(R.layout.rowcategory, parent, false);

        CategoryHolder categoryHolder = new CategoryHolder(view);

        return categoryHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryHolder categoryHolder, final int position) {

        final Category category = categoryList.get(position);
        categoryHolder.tvViTri.setText("" + category.getViTri() + "");
        categoryHolder.tvTenTL.setText("" + category.getTenTL());
        final CategoryDAO categoryDAO = new CategoryDAO(context);
        categoryList = categoryDAO.ALLCategory();

        categoryHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.Delete);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CategoryDAO categoryDao = new CategoryDAO(context);
                        categoryDao.delete(category.getMaTL());
                        categoryList.remove(position);
                        recyclerView.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, categoryList.size());
                        notifyDataSetChanged();
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

        categoryHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.usermenu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.menuxemchitiet:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                View dialog = LayoutInflater.from(context).inflate(R.layout.showdetailcategory, null);
                                builder.setView(dialog);
                                TextView tvDetailMaTL = dialog.findViewById(R.id.tvDetailMaTL);
                                TextView tvDetailTenTL = dialog.findViewById(R.id.tvDetailTenTL);
                                TextView tvDetailViTri = dialog.findViewById(R.id.tvDetailViTri);
                                TextView tvDetailMoTa = dialog.findViewById(R.id.tvDetailMoTa);

                                tvDetailMaTL.setText(category.getMaTL() + "");
                                tvDetailTenTL.setText(category.getTenTL());
                                tvDetailViTri.setText(category.getViTri() + "");
                                tvDetailMoTa.setText(category.getMoTa());
                                builder.create();
                                builder.show();
                                break;

                            case R.id.update:
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                                View dialog2 = LayoutInflater.from(context).inflate(R.layout.updatecategory, null);
                                builder2.setView(dialog2);
                                final EditText edtUpdateTenTL = dialog2.findViewById(R.id.edtUpdateTenTL);
                                final EditText edtUpdateViTri = dialog2.findViewById(R.id.edtUpdateViTri);
                                final EditText edtUpdateMota = dialog2.findViewById(R.id.edtUpdateMoTa);


                                edtUpdateTenTL.setText(category.getTenTL());
                                edtUpdateViTri.setText(category.getViTri() + "");
                                edtUpdateMota.setText(category.getMoTa());

                                builder2.setPositiveButton("OKe", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String TenTL = edtUpdateTenTL.getText().toString();
                                        int ViTri = Integer.parseInt(edtUpdateViTri.getText().toString());
                                        String MoTa = edtUpdateMota.getText().toString();
                                        if (TenTL.length() == 0 || String.valueOf(ViTri).length() == 0 || MoTa.length() == 0) {
                                            Toast.makeText(context, "Bạn Chưa Nhập TênTL hoặc Vị Trí hoặc Mô Tả", Toast.LENGTH_LONG).show();
                                        } else {
                                            category.setTenTL(TenTL);
                                            category.setViTri(ViTri);
                                            category.setMoTa(MoTa);
                                            if (categoryDAO.UpdateCategory(category) < 0) {
                                                Toast.makeText(context, "update that bai", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(context, "update thanh cong", Toast.LENGTH_LONG).show();
                                                notifyDataSetChanged();
                                            }
                                        }
                                    }

                                });


                                builder2.setNegativeButton("HUỷ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });


                                builder2.create();
                                builder2.show();
                                break;

                        }

                        return false;
                    }


                });

                popupMenu.show();
            }

        });
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }


}

