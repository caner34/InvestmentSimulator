package com.finance.portfollio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.finance.portfollio.AsyncTasks.ExchangeRatesRetriever;
import com.finance.portfollio.utils.AsyncTaskHelper;
import com.finance.portfollio.utils.CommonUtils;
import com.finance.portfollio.utils.GlobalVariables;

import java.util.Arrays;

public class ForeignExchangeActivity extends AppCompatActivity {
    TextView textViewRates;
    Spinner spinnerCountries;
    ArrayAdapter arrayAdapterCountries;

    private void InitializeComponents()
    {
        textViewRates = findViewById(R.id.rates);
        spinnerCountries = findViewById(R.id.country_spinner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_exchange);
        InitializeComponents();
        HandleSpinners();
        new ExchangeRatesRetriever(this).execute("https://api.exchangeratesapi.io/latest");

    }

    private void HandleSpinners() {
        HandleSpinnerCountries();
    }

    private void HandleSpinnerCountries()
    {
        arrayAdapterCountries = new ArrayAdapter(this, android.R.layout.simple_spinner_item, CommonUtils.GetSpinnerStringArrayWithHeaderTitle(spinnerCountries, Arrays.asList(GlobalVariables.CountryCodes), ""));
        spinnerCountries.setAdapter(arrayAdapterCountries);
        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    textViewRates.setText(R.string.message_display_foreign_rates);
                }
                else
                {
                    String rate = String.valueOf(GlobalVariables.CountryRates[i-1]);
                    rate = rate + " " + GlobalVariables.CountryCodes[i-1];
                    textViewRates.setText(rate);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}