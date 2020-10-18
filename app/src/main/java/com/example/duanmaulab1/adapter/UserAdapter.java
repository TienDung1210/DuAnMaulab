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

import com.example.duanmaulab1.dao.UserDAO;
import com.example.duanmaulab1.holder.UserHolder;
import com.example.duanmaulab1.model.User;
import com.example.duanmaulab1.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {

    public Context context;
    public List<User> userList;
    public RecyclerView recyclerView;


    public UserAdapter(Context context, List<User> userList, RecyclerView recyclerView) {

        this.context = context;
        this.userList = userList;
        this.recyclerView = recyclerView;
    }


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(context).
                        inflate(R.layout.rowuser, parent, false);

        UserHolder userHolder = new UserHolder(view);

        return userHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull final UserHolder userHolder, final int position) {

        final User user = userList.get(position);
        userHolder.tvFullName.setText("" + user.getFullName());
        userHolder.tvPhone.setText("" + user.getPhoneNumber());
        final UserDAO userDAO = new UserDAO(context);
        userList = userDAO.ALLUser();


        userHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
                                View dialog = LayoutInflater.from(context).inflate(R.layout.showdetailuser, null);
                                builder.setView(dialog);
                                TextView tvShowUsername = dialog.findViewById(R.id.tvShowUser);
                                TextView tvShowPass = dialog.findViewById(R.id.tvShowPass);
                                TextView tvShowPhone = dialog.findViewById(R.id.tvShowPhone);
                                TextView tvShowFullname = dialog.findViewById(R.id.tvShowFullName);

                                tvShowUsername.setText(user.getUserName());
                                tvShowPass.setText(user.getPassword());
                                tvShowPhone.setText(user.getPhoneNumber());
                                tvShowFullname.setText(user.getFullName());
                                builder.create();
                                builder.show();
                                break;

                            case R.id.update:
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                                View dialog2 = LayoutInflater.from(context).inflate(R.layout.updateuser, null);
                                builder2.setView(dialog2);
                                final EditText edtUpdatePhone = dialog2.findViewById(R.id.edtUpdatePhone);
                                final EditText edtUpdateFullname = dialog2.findViewById(R.id.edtUpdateFullName);


                                edtUpdatePhone.setText(user.getPhoneNumber());
                                edtUpdateFullname.setText(user.getFullName());

                                builder2.setPositiveButton("OKe", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String phone = edtUpdatePhone.getText().toString();
                                        String fullname = edtUpdateFullname.getText().toString();
                                        if (phone.length() == 0 || phone.length() < 10 || phone.length() > 10) {
                                            Toast.makeText(context, "bạn chưa nhập số điện thoại hoặc phone = 10 số", Toast.LENGTH_LONG).show();
                                        } else if (fullname.length() == 0) {
                                            Toast.makeText(context, "bạn chưa nhập họ và tên", Toast.LENGTH_LONG).show();
                                        } else {
                                            user.setPhoneNumber(phone);
                                            user.setFullName(fullname);
                                            if (userDAO.UpdateUser(user) < 0) {
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

        userHolder.ImgDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.Delete);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserDAO userDao = new UserDAO(context);
                        userDao.delete(user.getUserName());
                        userList.remove(position);
                        recyclerView.removeViewAt(position);
                        //Thông báo khi dữ liệu ở 1 vị trí bị gỡ bỏ
                        notifyItemRemoved(position);
                        // set lại số lượng phần tử từ vị trí bắt đầu thay đổi
                        notifyItemRangeChanged(position, userList.size());
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
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

}
