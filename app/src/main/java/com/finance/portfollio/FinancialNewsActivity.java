package com.finance.portfollio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import com.finance.portfollio.utils.AsyncTaskHelper;
import com.finance.portfollio.utils.FinancialNewsElement;
import com.finance.portfollio.utils.GlobalVariables.FINANCIAL_NEWS_SOURCE;
import java.util.List;


public class FinancialNewsActivity extends AppCompatActivity {

    TextView textViewFinancialNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_news);

        InitializeComponents();

        SetClickListeners();

        FillTheViewWithFinancialNews();
    }

    private void FillTheViewWithFinancialNews()
    {
        List<FinancialNewsElement> financialNewsElementList
                = AsyncTaskHelper.GetFinancialNewsElementsList(FINANCIAL_NEWS_SOURCE.FINANCIAL_TIMES);
        //TODO Sevket - Filling the view with the information for the financial news from the granted list of FinancialNewsElement Objects


    }

    private void SetClickListeners() {

    }

    private void InitializeComponents()
    {
        textViewFinancialNews = findViewById(R.id.textViewFinancialNews);

        //TODO Sevket - Adding corresponding components to the activity_financial_news.xml and initializing them
    }
}