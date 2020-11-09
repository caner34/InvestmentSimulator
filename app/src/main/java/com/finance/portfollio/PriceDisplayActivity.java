package com.finance.portfollio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.finance.portfollio.utils.AsyncTaskHelper;
import com.finance.portfollio.utils.CommonUtils;
import com.finance.portfollio.utils.GlobalVariables;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Arrays;


public class PriceDisplayActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    public MaterialToolbar toolbar_menu;
    TextView textViewPrice;
    Spinner spinnerStocks;
    ArrayAdapter arrayAdapterStocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_display);

        InitializeComponents();

        HandleSpinners();

        SetOnClickListeners();
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
        }
        return false;
    }
}