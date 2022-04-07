package com.example.project_team7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_team7.Model.MonAn;
import com.example.project_team7.R;


import java.util.ArrayList;

public class NguyenLieuAdapter extends RecyclerView.Adapter<NguyenLieuAdapter.MonAnViewHolder>
{
    private Context mContext;
    private ArrayList<MonAn> MonAnList;
    Fragment activity;

    public NguyenLieuAdapter(Context mContext, ArrayList<MonAn> monAnArrayList, Fragment activity) {
        this.mContext =mContext;
        this.MonAnList = monAnArrayList;
        this.activity = activity;
    }


    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_nguyen_lieu,parent,false);
        return new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, final int position) {
        holder.tvNguyenLieu.setText(MonAnList.get(position).getNguyenLieu());
        Glide.with(mContext).load(MonAnList.get(position).getImgNL()).into(holder.imgItemNguyenLieu);

    }

    @Override
    public int getItemCount() {
        return MonAnList.size();
    }

    public class MonAnViewHolder extends RecyclerView.ViewHolder{
        TextView tvNguyenLieu;
        ImageView imgItemNguyenLieu;
        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNguyenLieu = itemView.findViewById(R.id.tvFragment);
            imgItemNguyenLieu = itemView.findViewById(R.id.imgItemNguyenLieu);

        }
    }

}
