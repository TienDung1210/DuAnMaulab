package com.example.duanmaulab1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmaulab1.acitivity.AddBookActivivy;
import com.example.duanmaulab1.dao.BookDAO;
import com.example.duanmaulab1.dao.CategoryDAO;
import com.example.duanmaulab1.holder.BookHolder;
import com.example.duanmaulab1.model.Book;
import com.example.duanmaulab1.R;
import com.example.duanmaulab1.model.Category;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {
    public Context context;
    public List<Book> bookList;
    public RecyclerView recyclerView;
    public List<Category> categoryList;
    public String TenTL;


    public BookAdapter(Context context, List<Book> bookList, RecyclerView recyclerView) {
        this.context = context;
        this.bookList = bookList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(context).
                        inflate(R.layout.rowbook, parent, false);

        BookHolder bookHolder = new BookHolder(view);

        return bookHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull final BookHolder bookHolder, final int position) {

        final Book book = bookList.get(position);
        bookHolder.tvTenSach.setText("" + book.getTensach());
        bookHolder.tvGia.setText("" + book.getGiaBia());
        final BookDAO bookDAO = new BookDAO(context);


        bookHolder.imgDeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.Delete);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BookDAO bookDao = new BookDAO(context);
                        bookDao.deleteBook(book.getMasach());
                        bookList.remove(position);
                        recyclerView.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, bookList.size());
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

        bookHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
                                View dialog = LayoutInflater.from(context).inflate(R.layout.showdetailbook, null);
                                builder.setView(dialog);
                                TextView tvDetailTenTL = dialog.findViewById(R.id.tvDetailTenTLBook);
                                TextView tvDetailMaSach = dialog.findViewById(R.id.tvDetailMaSach);
                                TextView tvDetailTenSach = dialog.findViewById(R.id.tvDetailTenSach);
                                TextView tvDetailTacGia = dialog.findViewById(R.id.tvDetailTacGia);
                                TextView tvDetailNhaXB = dialog.findViewById(R.id.tvDetailNhaXB);
                                TextView tvDetailGiaBia = dialog.findViewById(R.id.tvDetailgiaBia);
                                TextView tvDetailSoLuong = dialog.findViewById(R.id.tvDetailSL);


                                tvDetailTenTL.setText(book.getTenTLBook());
                                tvDetailMaSach.setText(book.getMasach() + "");
                                tvDetailTenSach.setText(book.getTensach());
                                tvDetailTacGia.setText(book.getTacGia());
                                tvDetailNhaXB.setText(book.getNhaXuatBan());
                                tvDetailGiaBia.setText(book.getGiaBia() + "");
                                tvDetailSoLuong.setText(book.getSoLuong() + "");
                                builder.create();
                                builder.show();
                                break;

                            case R.id.update:
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                                View dialog2 = LayoutInflater.from(context).inflate(R.layout.updatebook, null);
                                builder2.setView(dialog2);

                                final Spinner spUpdateTenTL = dialog2.findViewById(R.id.spUpdateTenTL);
                                final EditText edtUpdateTenSach = dialog2.findViewById(R.id.edtUpdateTenSach);
                                final EditText edtUpdateTacGia = dialog2.findViewById(R.id.edtUpdateTenTacGia);
                                final EditText edtUpdateNhaXB = dialog2.findViewById(R.id.edtUpdateNhaXB);
                                final EditText edtUpdateGiaBia = dialog2.findViewById(R.id.edtUpdateGiaBia);
                                final EditText edtUpdateSL = dialog2.findViewById(R.id.edtUpdateSL);

                                categoryList = new ArrayList<>();
                                final CategoryDAO categoryDAO = new CategoryDAO(context);
                                categoryList = categoryDAO.ALLCategory();

                                CategorySpinnerAdapter categorySpinnerAdapter = new CategorySpinnerAdapter(context, categoryList);
                                spUpdateTenTL.setAdapter(categorySpinnerAdapter);

                                spUpdateTenTL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        TenTL = categoryList.get(spUpdateTenTL.getSelectedItemPosition()).getTenTL();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                        Toast.makeText(context, "Ban chua chon Ten The Loai", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                });

                                edtUpdateTenSach.setText(book.getTensach());
                                edtUpdateTacGia.setText(book.getTacGia());
                                edtUpdateNhaXB.setText(book.getNhaXuatBan());
                                edtUpdateGiaBia.setText(book.getGiaBia() + "");
                                edtUpdateSL.setText(book.getSoLuong() + "");

                                builder2.setPositiveButton("OKe", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {


                                        String TenSach = edtUpdateTenSach.getText().toString();
                                        String TacGia = edtUpdateTacGia.getText().toString();
                                        String NhaXB = edtUpdateNhaXB.getText().toString();
                                        double GiaBia = Double.parseDouble(edtUpdateGiaBia.getText().toString());
                                        double SL = Double.parseDouble(edtUpdateSL.getText().toString());


                                        book.setTenTLBook(TenTL);
                                        book.setTensach(TenSach);
                                        book.setTacGia(TacGia);
                                        book.setNhaXuatBan(NhaXB);
                                        book.setGiaBia(GiaBia);
                                        book.setSoLuong(SL);
                                        if (bookDAO.UpdateBook(book) < 0) {
                                            Toast.makeText(context, "update that bai", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(context, "update thanh cong", Toast.LENGTH_LONG).show();
                                            notifyDataSetChanged();
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
        return bookList.size();
    }


}
