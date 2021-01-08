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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.portfollio.AsyncTasks.FinancialNewsRetriever;
import com.finance.portfollio.utils.AsyncTaskHelper;
import com.finance.portfollio.utils.CommonUtils;
import com.finance.portfollio.utils.GlobalVariables;
import com.finance.portfollio.utils.NetworkChangeReceiver;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Arrays;


public class PriceDisplayActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener {
    Context context = this;
    public MaterialToolbar toolbar_menu;
    TextView textViewPrice;
    Spinner spinnerStocks;
    ArrayAdapter arrayAdapterStocks;

    NetworkChangeReceiver receiverNetwork;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_NETWORK_STATE = 1;

    Button price_display_sell_button;
    Button price_display_buy_button;

    String selected_stock_code = null;

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
                    AsyncTaskHelper.DisplayStockPriceOnTextView(PriceDisplayActivity.this, getApplicationContext(), arrayAdapterStocks.getItem(i).toString(), textViewPrice);
                    selected_stock_code = arrayAdapterStocks.getItem(i).toString();

                    if(selected_stock_code != null){
                        Log.e("E", "stock code PriceDisplayActivity HandleSpinnerStocks");
                        Log.e("E", arrayAdapterStocks.getItem(i).toString());
                    }
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
        price_display_sell_button.setOnClickListener(this);
        price_display_buy_button.setOnClickListener(this);

    }

    private void InitializeComponents()
    {
        toolbar_menu = findViewById(R.id.activity_price_display_toolbar);
        textViewPrice = findViewById(R.id.textViewPrice);
        spinnerStocks = findViewById(R.id.spinnerStocks);
        price_display_sell_button = findViewById(R.id.price_display_sell_button);
        price_display_buy_button = findViewById(R.id.price_display_buy_button);
    }

    @Override
    public void onClick(View v) {
        Intent intent_purchase = new Intent(PriceDisplayActivity.this, PurchaseActivity.class);
        switch (v.getId()) {
            case R.id.price_display_sell_button:
                intent_purchase.putExtra("process", "sell");
                break;
            case R.id.price_display_buy_button:
                if(selected_stock_code != null){
                    intent_purchase.putExtra("process", "buy");
                    intent_purchase.putExtra("selected_stock_code", selected_stock_code);
                } else {
                    Toast.makeText(context, "Please select a stock code!!!", Toast.LENGTH_SHORT).show();
                    Log.e("E", "stock code null PriceDisplayActivity");
                }
                break;
        }
        startActivity(intent_purchase);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.financial_news_menu:
                if(networkIsOn()){
                    new FinancialNewsRetriever(context).execute("https://economictimes.indiatimes.com/news/economy/rssfeeds/1373380680.cms");
                } else {
                    Toast.makeText(context, "Connection fail!!!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.foreign_exchange_menu:
                if(networkIsOn()){
                    Intent intent_foreign = new Intent(PriceDisplayActivity.this, ForeignExchangeActivity.class);
                    startActivity(intent_foreign);
                } else {
                    Toast.makeText(context, "Connection fail!!!", Toast.LENGTH_SHORT).show();
                }
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