package com.example.project_team7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TimMonAnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_mon_an);
        getSupportActionBar().hide();
    }
}