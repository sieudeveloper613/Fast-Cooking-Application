package com.example.project_team7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project_team7.Model.MonAn;
import com.example.project_team7.R;

import java.util.ArrayList;

public class CachLamAdapter extends RecyclerView.Adapter<CachLamAdapter.MonAnViewHolder>
{
    private Context mContext;
    private ArrayList<MonAn> MonAnList;
    Fragment activity;

    public CachLamAdapter(Context mContext, ArrayList<MonAn> monAnArrayList, Fragment activity) {
        this.mContext =mContext;
        this.MonAnList = monAnArrayList;
        this.activity = activity;
    }


    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_cach_lam,parent,false);
        return new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, final int position) {
        holder.tvCachLam.setText(MonAnList.get(position).getCachLam());

    }

    @Override
    public int getItemCount() {
        return MonAnList.size();
    }

    public class MonAnViewHolder extends RecyclerView.ViewHolder{
        TextView tvCachLam;
        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCachLam = itemView.findViewById(R.id.tvFragment);

        }
    }

}
