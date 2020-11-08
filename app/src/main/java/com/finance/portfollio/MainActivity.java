package com.finance.portfollio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.finance.portfollio.utils.GlobalVariables;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LoadAndAssignGlobalVariables();

        Intent i = new Intent(MainActivity.this, PriceDisplayActivity.class);
        startActivity(i);

    }

    private void LoadAndAssignGlobalVariables() {
        GlobalVariables.r = new Random();
    }
}