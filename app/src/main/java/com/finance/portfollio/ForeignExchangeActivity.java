package com.finance.portfollio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.finance.portfollio.AsyncTasks.ExchangeRatesRetriever;
import com.finance.portfollio.utils.CommonUtils;
import com.finance.portfollio.utils.GlobalVariables;

import java.util.Arrays;

public class ForeignExchangeActivity extends AppCompatActivity implements View.OnClickListener {
    Context context = this;
    Button foreign_exchange_calculate_button;
    Spinner base_country_spinner;
    Spinner rate_country_spinner;
    ArrayAdapter arrayAdapterBaseCountries;
    ArrayAdapter arrayAdapterRateCountries;
    EditText edt_txt_amount;

    String from_country;
    String to_country;
    double from_rate = -1;
    double to_rate = -1;


    public void init() {
        // Changing status bar color
        Window window = ForeignExchangeActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(ForeignExchangeActivity.this, R.color.layout_background_color));
        // init ui
        edt_txt_amount = findViewById(R.id.edt_txt_amount);
        foreign_exchange_calculate_button = findViewById(R.id.foreign_exchange_calculate_button);
        foreign_exchange_calculate_button.setOnClickListener(this);
        base_country_spinner = findViewById(R.id.base_country_spinner);
        rate_country_spinner = findViewById(R.id.rate_country_spinner);

        handleSpinnerCountries();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_exchange);
        init();
        new ExchangeRatesRetriever(this).execute("https://api.exchangeratesapi.io/latest");
    }

    private void handleSpinnerCountries()
    {
        arrayAdapterBaseCountries = new ArrayAdapter(this, android.R.layout.simple_spinner_item, CommonUtils.GetSpinnerStringArrayWithHeaderTitle(base_country_spinner, Arrays.asList(GlobalVariables.CountryCodes), ""));
        base_country_spinner.setAdapter(arrayAdapterBaseCountries);
        base_country_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    from_rate = -1;
                }
                else
                {
                    from_rate = GlobalVariables.CountryRates[i-1];
                    from_country = GlobalVariables.CountryCodes[i-1];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        arrayAdapterRateCountries = new ArrayAdapter(this, android.R.layout.simple_spinner_item, CommonUtils.GetSpinnerStringArrayWithHeaderTitle(rate_country_spinner, Arrays.asList(GlobalVariables.CountryCodes), ""));
        rate_country_spinner.setAdapter(arrayAdapterRateCountries);
        rate_country_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    to_rate = -1;
                }
                else
                {
                    to_rate = GlobalVariables.CountryRates[i-1];
                    to_country = GlobalVariables.CountryCodes[i-1];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.foreign_exchange_calculate_button) {
            if(from_rate == -1 || to_rate == -1) {
                Toast.makeText(context, "Select Countries!!!", Toast.LENGTH_SHORT).show();
            } else if(edt_txt_amount.getText().toString().isEmpty()) {
                Toast.makeText(context, "Enter Amount!!", Toast.LENGTH_SHORT).show();
            } else {
                double ratio_base = to_rate / from_rate;
                double amount = Double.parseDouble(edt_txt_amount.getText().toString());
                double user_ratio = ratio_base * amount;
                AlertDialog.Builder adb = new AlertDialog.Builder(context);
                @SuppressLint("DefaultLocale") String result_title = String.format("%.2f", (ratio_base));
                @SuppressLint("DefaultLocale") String result_message = String.format("%.2f", (user_ratio));
                adb.setTitle("1" + from_country + " = " + result_title + " " + to_country);
                adb.setMessage(amount + " " + from_country + " = "  + result_message + " " + to_country);
                adb.setPositiveButton("OK", null);
                adb.show();
            }
        }
    }
}
