package com.finance.portfollio.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.finance.portfollio.FinancialNewsActivity;
import com.finance.portfollio.PriceDisplayActivity;
import com.finance.portfollio.utils.GlobalVariables;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//TODO Caner - Implementing the AsyncTask in order to retrieve FinancialNewsElements
public class FinancialNewsRetriever extends AsyncTask<String, Integer, String> {
    public static final String FINANCIAL_NEWS_LOG_TAG = "FINANCIAL_NEWS";
    Context context;
    ProgressDialog progressDialog;


    public FinancialNewsRetriever(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Financial News");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            // IMPORTANT!!!
            // RSS is a free service offered by TIL to individuals for personal, non-commercial use.
            // https://economictimes.indiatimes.com/rss.cms
            // https://economictimes.indiatimes.com/news/economy/rssfeeds/1373380680.cms
            Document doc = Jsoup.connect("https://economictimes.indiatimes.com/news/economy/rssfeeds/1373380680.cms").get();

            GlobalVariables.FinancialNewsElements = doc.select("item");

            Log.e(FINANCIAL_NEWS_LOG_TAG,"News Cnt : "+ GlobalVariables.FinancialNewsElements.size());
        } catch (Exception e) {
            Log.e(FINANCIAL_NEWS_LOG_TAG,"ERROR FETCHING RSS : "+e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(context, FinancialNewsActivity.class);
        context.startActivity(intent);
        progressDialog.dismiss();
    }
}
