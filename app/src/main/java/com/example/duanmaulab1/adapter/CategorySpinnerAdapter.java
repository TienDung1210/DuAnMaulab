package com.example.duanmaulab1.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.duanmaulab1.R;
import com.example.duanmaulab1.model.Category;

import java.util.List;

public class CategorySpinnerAdapter implements SpinnerAdapter {

    private List<Category> categoryList;
    private Context context;

    public CategorySpinnerAdapter(Context context, List<Category> loaiThuList) {
        this.context = context;
        this.categoryList = loaiThuList;
    }

    // getDropDownView: được gọi ra khi control hiển thị các lựa
    // chọn khi người dùng bấm vào Spinner
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.spinnercategory, parent, false);
        TextView tvSPTenTL = convertView.findViewById(R.id.tvSPTenTL);
        tvSPTenTL.setText(categoryList.get(position).getTenTL());
        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Category getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    // getView: được gọi ra khi control cần phải hiển thị view cho
    //người dùng (khi vẽ lại hoặc cập nhật)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.spinnercategory, parent, false);
        TextView tvSPTenTL = convertView.findViewById(R.id.tvSPTenTL);
        tvSPTenTL.setText(categoryList.get(position).getTenTL());
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
