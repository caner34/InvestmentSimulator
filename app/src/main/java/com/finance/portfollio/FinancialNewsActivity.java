package com.finance.portfollio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.finance.portfollio.ViewAdapters.FinancialNewsRecyclerViewAdapter;
import com.finance.portfollio.utils.GlobalVariables;
import com.finance.portfollio.utils.RecyclerItemClickListener;

public class FinancialNewsActivity extends AppCompatActivity {
    Context context = this;
    RecyclerView financial_news_recycler_view;
    FinancialNewsRecyclerViewAdapter financialNewsRecyclerViewAdapter;

    private void InitializeComponents()
    {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        financialNewsRecyclerViewAdapter = new FinancialNewsRecyclerViewAdapter(context);

        financial_news_recycler_view = findViewById(R.id.financial_news_recycler_view);
        financial_news_recycler_view.setLayoutManager(llm);
        financial_news_recycler_view.setAdapter(financialNewsRecyclerViewAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_news);
        InitializeComponents();

        financial_news_recycler_view.addOnItemTouchListener(new RecyclerItemClickListener(context,
                financial_news_recycler_view,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String url = GlobalVariables.FinancialNewsElements.get(position).select("link").text();
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));
    }

}