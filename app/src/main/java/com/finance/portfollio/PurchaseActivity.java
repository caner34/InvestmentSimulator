package com.finance.portfollio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PurchaseActivity extends AppCompatActivity {

    // TODO -Sevket - Purchase Activity is a full-fledged activity which enables the user to purchase Ask/Bid (Sell/Puy) stocks and FOREX (foreign exchange).
    // TODO -Sevket - Purchase Activity GUI can enable the user to switch between Buy and Sell Screens Dynamically
    // TODO -Sevket - Sell screen should display current stocks of the user to be sold on the market.
    // TODO -Sevket - Buy screen should be able to filter stocks by its sector.
    // TODO -Sevket - Bonus User might add some stocks to his/her favorites.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
    }
}