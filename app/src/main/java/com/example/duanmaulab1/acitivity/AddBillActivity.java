package com.example.duanmaulab1.acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmaulab1.R;
import com.example.duanmaulab1.adapter.BillAdapter;
import com.example.duanmaulab1.adapter.BookAdapter;
import com.example.duanmaulab1.adapter.BookSpinnerAdapter;
import com.example.duanmaulab1.adapter.CategorySpinnerAdapter;
import com.example.duanmaulab1.dao.BillDAO;
import com.example.duanmaulab1.dao.BookDAO;
import com.example.duanmaulab1.dao.CategoryDAO;
import com.example.duanmaulab1.model.Bill;
import com.example.duanmaulab1.model.Book;
import com.example.duanmaulab1.model.Category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddBillActivity extends AppCompatActivity {
    EditText edtDate, edtSL;
    List<Book> bookList = new ArrayList<>();
    static List<Bill> billList = new ArrayList<>();
    BillDAO billDAO;
    Spinner spinnerTenSach;
    BookDAO bookDAO;
    RecyclerView recyclerView;
    BillAdapter billAdapter;
    String TenSach;
    static TextView tvMoneyBill;
    static double SoLuong;
    double GiaTien;
    Date date;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        setTitle("Thêm Hóa Đơn");
        Unit();
        billDAO = new BillDAO(this);
        // lay mac dinh ngay hien tai
        final Calendar calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edtDate.setText(simpleDateFormat.format(calendar.getTime()));
        // hiển thị tất cả tên sách có trong bảng sách
        bookDAO = new BookDAO(this);
        bookList = bookDAO.ALLBOOK();

        BookSpinnerAdapter bookSpinnerAdapter = new BookSpinnerAdapter(AddBillActivity.this, bookList);
        spinnerTenSach.setAdapter(bookSpinnerAdapter);

        // lay vi tri va lay ten sach da chon de them vao sqlite
        spinnerTenSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TenSach = bookList.get(spinnerTenSach.getSelectedItemPosition()).getTensach();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Ban chua chon Sach", Toast.LENGTH_LONG).show();
            }
        });


    }
           // chuyển đổi dữ liệu từ kiểu String thành Date
    public void btnAddBill(View view) throws ParseException {
        // goi model book = vi tri cua spinner ten sach da chon sau do lay du lieu tuong ung tu ten sach do
        Book book = (Book) spinnerTenSach.getSelectedItem();
        GiaTien = Double.parseDouble(book.getGiaBia() + "");
        double SoLuongBook = Double.parseDouble(book.getSoLuong() + "");
        date = simpleDateFormat.parse(edtDate.getText().toString());
        SoLuong = Double.parseDouble(edtSL.getText().toString());

        if (String.valueOf(SoLuong).length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập số lượng", Toast.LENGTH_LONG).show();
        } else if (SoLuong > SoLuongBook) {
            Toast.makeText(getApplicationContext(), "Số lượng nhập lớn hơn Số lượng Trong kho, vui lòng nhâp lại !", Toast.LENGTH_LONG).show();
        } else if (SoLuong < 0) {
            Toast.makeText(getApplicationContext(), "Số lượng >0 !", Toast.LENGTH_LONG).show();
        } else {
            Bill bill = new Bill(date, TenSach, SoLuong, GiaTien);
            billList.add(bill);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            billAdapter = new BillAdapter(this, billList, recyclerView);
            recyclerView.setAdapter(billAdapter);
            SetTongTien();

        }

    }

    // gọi phương thức giữa các activity với nhau mà không phải tạo lại
    public static void SetTongTien() {
        double TongTien = 0;
        for (int i = 0; i < billList.size(); i++) {

            // Tong Tien = Tong Tien + (Gia Tien tai vi tri thu i * SL do người đùng nhập vào)
            TongTien += billList.get(i).getGiaTien() * SoLuong;
        }
        tvMoneyBill.setText(TongTien + " " + "VNĐ");
    }

    public void Unit() {
        edtDate = findViewById(R.id.edtDate);
        edtSL = findViewById(R.id.edtSoLuongBill);
        spinnerTenSach = findViewById(R.id.spTenSach);
        tvMoneyBill = findViewById(R.id.tvMoneyBill);
        recyclerView = findViewById(R.id.rclAddBill);
    }


    public void btnThanhToan(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.ThanhToan);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ((billList.size() < 0)) {
                    Toast.makeText(getApplicationContext(), "Hóa đơn của bạn chưa có gì ? vui lòng thử lại", Toast.LENGTH_LONG).show();

                } else {

                    for(int i = 0; i<billList.size();i++){
                        Bill bill = new Bill(date, TenSach, SoLuong, GiaTien);
                        if ( billDAO.InsertBill(bill) < 0) {
                            Toast.makeText(getApplicationContext(), "Thanh Toán Thất Bại", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Thanh Toán Thành Công", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AddBillActivity.this, BillActivity.class);
                            startActivity(intent);
                        }
                        }
                        billList.clear();
                    }
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

    public void btnHuyHD(View view) {
        billList.clear();
        Intent intent = new Intent(AddBillActivity.this, BillActivity.class);
        startActivity(intent);
        finish();
    }
}
