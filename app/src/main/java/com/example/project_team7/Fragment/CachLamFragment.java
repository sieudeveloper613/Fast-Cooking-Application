package com.example.project_team7.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project_team7.Adapter.CachLamAdapter;
import com.example.project_team7.Model.MonAn;
import com.example.project_team7.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CachLamFragment extends Fragment {
    String  idMonAn, type;
    TextView tvCachLam, tvLevel, tvTime;
    ImageView imgCachLam;
    private DatabaseReference monAnRef;
    private CachLamAdapter clAdapter;
    private ArrayList<MonAn> MonAnList;
    RecyclerView rcvCachLam;


    public CachLamFragment(String idMonAn, String type) {

        this.idMonAn = idMonAn;
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cach_lam,container,false);
        rcvCachLam =v.findViewById(R.id.rcvCachLam);
        tvCachLam = v.findViewById(R.id.tvCachLam);
        imgCachLam = v.findViewById(R.id.imgCachLam);
        tvLevel = v.findViewById(R.id.tvLevel);
        tvTime = v.findViewById(R.id.tvTime);

        LinearLayoutManager layout =new LinearLayoutManager(getContext());
        rcvCachLam.setLayoutManager(layout);
        MonAnList = new ArrayList<>();

        monAnRef = FirebaseDatabase.getInstance().getReference().child(type);
        monAnRef.child(idMonAn).child("Ten").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvCachLam.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        monAnRef.child(idMonAn).child("Image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.getValue(String.class);
                Picasso.get().load(link).into(imgCachLam);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        monAnRef.child(idMonAn).child("Level").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvLevel.setText("Mức độ: "+snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        monAnRef.child(idMonAn).child("Time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvTime.setText("Thời gian: "+snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        monAnRef.child(idMonAn).child("CachLam").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()){
                    MonAn monAn = new MonAn();
                    monAn.setCachLam(snapshot1.child("noidung").getValue().toString());
                    MonAnList.add(monAn);
                }
                clAdapter = new CachLamAdapter(getContext(),MonAnList,CachLamFragment.this);
                rcvCachLam.setAdapter(clAdapter);
                clAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return v;

    }

}
