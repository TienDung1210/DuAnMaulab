package com.example.duanmaulab1.holder;

        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.duanmaulab1.R;

public class BillHoder extends RecyclerView.ViewHolder {
    public TextView tvTenSach, tvGia, tvSoLuong;
    public ImageView ImgDeleteBill;


    public BillHoder(@NonNull View itemView) {
        super(itemView);
        tvTenSach = itemView.findViewById(R.id.tvDetailTenSachBill);
        tvSoLuong = itemView.findViewById(R.id.tvDetailSLBill);
        tvGia = itemView.findViewById(R.id.tvDetailMoneyBill);
        ImgDeleteBill = itemView.findViewById(R.id.ImgDeleteBill);

    }
}
