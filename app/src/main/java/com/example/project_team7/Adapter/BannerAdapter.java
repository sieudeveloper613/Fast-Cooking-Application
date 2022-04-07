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
import com.example.project_team7.Fragment.BannerFragment;
import com.example.project_team7.Model.MonAn;
import com.example.project_team7.R;


import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MonAnViewHolder>
{
    private Context mContext;
    private ArrayList<MonAn> MonAnList;
    BannerFragment activity;
    String type;

    public BannerAdapter(Context mContext, ArrayList<MonAn> monAnArrayList, BannerFragment activity, String type) {
        this.mContext =mContext;
        this.MonAnList = monAnArrayList;
        this.activity = activity;
        this.type = type;
    }


    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mon_an_banner,parent,false);
        return new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, final int position) {
        holder.tvTen.setText(MonAnList.get(position).getTen());
        Glide.with(mContext).load(MonAnList.get(position).getImg()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.gotoMonAn(MonAnList.get(position).getId(),type);
            }
        });

    }

    @Override
    public int getItemCount() {
        return MonAnList.size();
    }

    public class MonAnViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tvTen;
        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgBanner);
            tvTen = itemView.findViewById(R.id.tvTenBanner);
        }
    }

}
