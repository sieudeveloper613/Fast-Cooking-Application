package com.example.project_team7.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project_team7.Adapter.MenuAdapter;
import com.example.project_team7.Model.MonAn;
import com.example.project_team7.MonAnnActivity;
import com.example.project_team7.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuFragment extends Fragment {
    private DatabaseReference dbRef;
    ArrayList<MonAn> MonAnList;
    MenuAdapter TopMonAdapter;
    RecyclerView rcvTopMon;
    SearchView searchView;
    String type;

    public MenuFragment(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_me_nu, container, false);
        rcvTopMon = v.findViewById(R.id.rcvMenu);
        searchView = v.findViewById(R.id.searchView);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rcvTopMon.setLayoutManager(layoutManager);

        MonAnList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference().child(type);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MonAn monAn = new MonAn();
                    monAn.setId(snapshot.child("Id").getValue().toString());
                    monAn.setImg(snapshot.child("Image").getValue().toString());
                    monAn.setTen(snapshot.child("Ten").getValue().toString());
                    Log.d("Loi", "onDataChange: " + snapshot.child("Id").getValue().toString());
                    MonAnList.add(monAn);

                }
                TopMonAdapter = new MenuAdapter(getContext(), MonAnList, MenuFragment.this, type);
                rcvTopMon.setAdapter(TopMonAdapter);
                TopMonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return true;
            }
        });
        return v;

    }

    public void search(final String str) {
        MonAnList = new ArrayList<>();
        ArrayList<MonAn> List = new ArrayList<>();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MonAn monAn = new MonAn();
                    monAn.setId(snapshot.child("Id").getValue().toString());
                    monAn.setImg(snapshot.child("Image").getValue().toString());
                    monAn.setTen(snapshot.child("Ten").getValue().toString());
                    Log.d("Loi", "onDataChange: " + snapshot.child("Id").getValue().toString());
                    if (monAn.getTen().toLowerCase().contains(str.toLowerCase())) {
                        MonAnList.add(monAn);
                    }
                }

                TopMonAdapter = new MenuAdapter(getContext(), MonAnList, MenuFragment.this, type);
                rcvTopMon.setAdapter(TopMonAdapter);
                TopMonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void gotoMonAn(String idMonAn, String type) {
        Intent intent = new Intent(getActivity(), MonAnnActivity.class);

        intent.putExtra("Id", idMonAn);
        intent.putExtra("Type", type);
        startActivity(intent);
    }
}
