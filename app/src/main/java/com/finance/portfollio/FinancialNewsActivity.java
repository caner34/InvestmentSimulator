package com.finance.portfollio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.finance.portfollio.ViewAdapters.FinancialNewsRecyclerViewAdapter;
import com.finance.portfollio.utils.AsyncTaskHelper;
import com.finance.portfollio.utils.FinancialNewsElement;
import com.finance.portfollio.utils.GlobalVariables.FINANCIAL_NEWS_SOURCE;
import java.util.List;


public class FinancialNewsActivity extends AppCompatActivity {
    Context context = this;
    RecyclerView financial_news_recycler_view;
    FinancialNewsRecyclerViewAdapter financialNewsRecyclerViewAdapter;


    private void InitializeComponents()
    {
        financial_news_recycler_view = findViewById(R.id.financial_news_recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        financial_news_recycler_view.setLayoutManager(llm);

        List<FinancialNewsElement> financialNewsElementList = AsyncTaskHelper.GetFinancialNewsElementsList(FINANCIAL_NEWS_SOURCE.FINANCIAL_TIMES);

        financialNewsRecyclerViewAdapter = new FinancialNewsRecyclerViewAdapter(context, financialNewsElementList);
        financial_news_recycler_view.setAdapter(financialNewsRecyclerViewAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_news);
        InitializeComponents();
    }


}