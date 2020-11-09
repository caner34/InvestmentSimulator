package com.finance.portfollio.AsyncTasks;


import android.os.AsyncTask;

import org.json.JSONArray;

import java.util.ArrayList;


// Exchange rates API: https://github.com/exchangeratesapi/exchangeratesapi
public class ExchangeRatesRetriever extends AsyncTask<JSONArray, Void, ArrayList> {

    public ExchangeRatesRetriever() {

    }

    @Override
    protected ArrayList doInBackground(JSONArray... jsonArrays) {

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
    }
}
