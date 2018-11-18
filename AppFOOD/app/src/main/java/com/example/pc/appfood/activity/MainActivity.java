package com.example.pc.appfood.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pc.appfood.R;

public class MainActivity extends AppCompatActivity {
    public static final String EmailHD = "email";
    public static final String PassHD = "pass";
    String pass= null, email =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
