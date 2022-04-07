package com.example.project_team7.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project_team7.Adapter.NguyenLieuAdapter;
import com.example.project_team7.Model.MonAn;
import com.example.project_team7.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NguyenLieuFragment extends Fragment {
    String  idMonAn,type;
    TextView tvNguyenLieu, tvLevel, tvTime;
    ImageView imgNguyenLieu;
    private DatabaseReference monAnRef;
    private NguyenLieuAdapter nlAdapter;
    private ArrayList<MonAn> MonAnList;
    RecyclerView rcv;

    public NguyenLieuFragment(String idMonAn, String type) {
        this.idMonAn = idMonAn;
        this.type = type;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nguyen_lieu,container,false);

        rcv =v.findViewById(R.id.rcvNguyenLieu);
        tvNguyenLieu = v.findViewById(R.id.tvNguyenLieu);
        imgNguyenLieu = v.findViewById(R.id.imgNguyenLieu);
        tvLevel = v.findViewById(R.id.tvLevel);
        tvTime = v.findViewById(R.id.tvTime);

        LinearLayoutManager layout =new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layout);
        MonAnList = new ArrayList<>();

        monAnRef = FirebaseDatabase.getInstance().getReference().child(type);

        monAnRef.child(idMonAn).child("Ten").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvNguyenLieu.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        monAnRef.child(idMonAn).child("Image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.getValue(String.class);
                Picasso.get().load(link).into(imgNguyenLieu);
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

        monAnRef.child(idMonAn).child("NguyenLieu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()){
                    MonAn monAn = new MonAn();
                    monAn.setNguyenLieu(snapshot1.child("noidung").getValue().toString());
                    monAn.setImgNL(snapshot1.child("img").getValue().toString());
                    MonAnList.add(monAn);
                }
                nlAdapter = new NguyenLieuAdapter(getContext(),MonAnList,NguyenLieuFragment.this);
                rcv.setAdapter(nlAdapter);
                nlAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }
}
