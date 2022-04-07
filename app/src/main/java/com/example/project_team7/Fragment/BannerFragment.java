package com.example.project_team7.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project_team7.Adapter.BannerAdapter;
import com.example.project_team7.Model.MonAn;
import com.example.project_team7.MonAnnActivity;
import com.example.project_team7.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BannerFragment extends Fragment {
    private DatabaseReference dbRef;
    private ArrayList<MonAn> MonAnList;
    private BannerAdapter BannerAdapter;
    RecyclerView rcvBanner;
    String type;

    public BannerFragment(String type) {
        this.type=type;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top_mon,container,false);
        rcvBanner = v.findViewById(R.id.rcvTopMon);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rcvBanner.setLayoutManager(layoutManager);

        MonAnList = new ArrayList<>();

        dbRef= FirebaseDatabase.getInstance().getReference().child(type);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    MonAn monAn = new MonAn();
                    monAn.setId(snapshot.child("Id").getValue().toString());
                    monAn.setImg(snapshot.child("Image").getValue().toString());
                    monAn.setTen(snapshot.child("Ten").getValue().toString());
                    Log.d("Loi", "onDataChange: "+snapshot.child("Id").getValue().toString());
                    MonAnList.add(monAn);

                }
                BannerAdapter = new BannerAdapter(getContext(),MonAnList, BannerFragment.this,type);
                rcvBanner.setAdapter(BannerAdapter);
                BannerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    public void gotoMonAn(String idMonAn,String type){
        Intent intent = new Intent(getActivity(), MonAnnActivity.class);

        intent.putExtra("Id", idMonAn);
        intent.putExtra("Type", type);
        startActivity(intent);
    }
}
