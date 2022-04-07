package com.example.project_team7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import com.example.project_team7.Fragment.CachLamFragment;
import com.example.project_team7.Fragment.NguyenLieuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MonAnnActivity extends AppCompatActivity {

    String idMonAn, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_ann);
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //lays id
        Intent intent = getIntent();
        idMonAn = intent.getStringExtra("Id");
        type = intent.getStringExtra("Type");

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new NguyenLieuFragment(idMonAn,type)).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.page_1:
                            selectedFragment = new NguyenLieuFragment(idMonAn,type);
                            break;
                        case R.id.page_2:
                            selectedFragment = new CachLamFragment(idMonAn,type);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };
}