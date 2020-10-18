package com.example.duanmaulab1.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.duanmaulab1.R;
import com.example.duanmaulab1.model.Book;
import com.example.duanmaulab1.model.Category;

import java.util.List;

public class BookSpinnerAdapter implements SpinnerAdapter {

    private List<Book> bookList;
    private Context context;

    public BookSpinnerAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }


    // chế độ xem khi mở spinner
    // có thể tùy chỉnh thêm
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup viewGroup) {
        convertView = LayoutInflater.from(context).inflate(R.layout.spinnerbook, viewGroup, false);
        TextView tvSPTenBook = convertView.findViewById(R.id.tvSPTenBook);
        tvSPTenBook.setText(bookList.get(position).getTensach());
        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return bookList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    // hiển thị đối tượng spinner sau khi chọn xong
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = LayoutInflater.from(context).inflate(R.layout.spinnerbook, viewGroup, false);
        TextView tvSPTenBook = convertView.findViewById(R.id.tvSPTenBook);
        tvSPTenBook.setText(bookList.get(position).getTensach());
        return convertView;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }


    // trả về số hàng
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
