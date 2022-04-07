package com.example.project_team7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.example.project_team7.Adapter.BannerAdapter;
import com.example.project_team7.Adapter.TopMonAdapter;
import com.example.project_team7.Fragment.BannerFragment;
import com.example.project_team7.Fragment.TopMonFragment;
import com.example.project_team7.Model.MonAn;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView rcvBanner;
    DatabaseReference dbRef;
    ArrayList<MonAn> MonAnList;
    private BannerAdapter bnAdapter;
    private TopMonAdapter MonChienAdapter, MonNuongAdapter;
    private Context mContext;
    ImageView imgChien, imgXao, imgLau, imgHap, imgNuong;
    EditText edNhapMonAn;
    ImageButton btnTimMonAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        imgChien = findViewById(R.id.imgChien);
        imgXao = findViewById(R.id.imgXao);
        imgLau = findViewById(R.id.imgLau);
        imgHap = findViewById(R.id.imgHap);
        imgNuong = findViewById(R.id.imgNuong);






        imgChien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu("MonAnChien");
            }
        });

        imgXao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu("MonAnXao");
            }
        });

        imgLau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu("MonAnLau");
            }
        });

        imgHap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu("MonAnHap");
            }
        });

        imgNuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu("MonAnNuong");
            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference();

        MonAnList = new ArrayList<>();


        getSupportFragmentManager().beginTransaction().add(R.id.fragment_Banner, new BannerFragment("MonAnDB")).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_monchien, new TopMonFragment("MonAnChien")).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_monxao, new TopMonFragment("MonAnXao")).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_monlau, new TopMonFragment("MonAnLau")).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_monhap, new TopMonFragment("MonAnHap")).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_monnuong, new TopMonFragment("MonAnNuong")).commit();
    }
    public void gotoMenu(String type){
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

}