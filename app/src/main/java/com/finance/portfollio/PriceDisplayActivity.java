package com.finance.portfollio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.portfollio.utils.AsyncTaskHelper;
import com.finance.portfollio.utils.CommonUtils;
import com.finance.portfollio.utils.GlobalVariables;
import com.finance.portfollio.utils.NetworkChangeReceiver;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Arrays;


public class PriceDisplayActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    Context context = this;
    public MaterialToolbar toolbar_menu;
    TextView textViewPrice;
    Spinner spinnerStocks;
    ArrayAdapter arrayAdapterStocks;

    NetworkChangeReceiver receiverNetwork;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_NETWORK_STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_display);
        if(!(ContextCompat.checkSelfPermission(PriceDisplayActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED)){
            requestAccessNetworkState();
        }

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiverNetwork = new NetworkChangeReceiver();
        registerReceiver(receiverNetwork, filter);

        InitializeComponents();

        HandleSpinners();

        SetOnClickListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!networkIsOn()){
            Toast.makeText(context, "Connection fail!!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverNetwork);
    }

    private void HandleSpinners() {
        HandleSpinnerStocks();
    }

    private void HandleSpinnerStocks()
    {
        arrayAdapterStocks = new ArrayAdapter(this, android.R.layout.simple_spinner_item, CommonUtils.GetSpinnerStringArrayWithHeaderTitle(spinnerStocks, Arrays.asList(GlobalVariables.StockCodes), ""));
        spinnerStocks.setAdapter(arrayAdapterStocks);
        spinnerStocks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    textViewPrice.setText(R.string.message_display_stock_price);
                }
                else
                {
                    // textViewPrice.setText(arrayAdapterStocks.getItem(i).toString());
                    AsyncTaskHelper.DisplayStockPriceOnTextView(PriceDisplayActivity.this, getApplicationContext(), arrayAdapterStocks.getItem(i).toString(), textViewPrice);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void SetOnClickListeners()
    {
        toolbar_menu.setOnMenuItemClickListener(this);
        /*buttonDisplayPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTaskHelper.DisplayStockPriceOnTextView(PriceDisplayActivity.this, getApplicationContext(), "INTC", textViewPrice);
            }
        });*/
    }

    private void InitializeComponents()
    {
        toolbar_menu = findViewById(R.id.activity_price_display_toolbar);
        textViewPrice = findViewById(R.id.textViewPrice);
        spinnerStocks = findViewById(R.id.spinnerStocks);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.financial_news_menu:
                Intent intent = new Intent(PriceDisplayActivity.this, FinancialNewsActivity.class);
                startActivity(intent);
                break;
            case R.id.foreign_exchange_menu:
                Intent intent_foreign = new Intent(PriceDisplayActivity.this, ForeignExchangeActivity.class);
                startActivity(intent_foreign);
                break;
        }
        return false;
    }

    boolean networkIsOn(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void requestAccessNetworkState(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_NETWORK_STATE)){
            ActivityCompat.requestPermissions(PriceDisplayActivity.this,new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, MY_PERMISSIONS_REQUEST_ACCESS_NETWORK_STATE);
        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, MY_PERMISSIONS_REQUEST_ACCESS_NETWORK_STATE);
        }
    }
}