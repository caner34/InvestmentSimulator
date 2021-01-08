package com.finance.portfollio.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.finance.portfollio.utils.GlobalVariables;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class ExchangeRatesRetriever extends AsyncTask<String, Integer, JSONObject> {
    public static final String FOREIGN_EXCHANGE_LOG_TAG = "FOREIGN_EXCHANGE";
    JSONObject jsonObject;
    Context context;

    public static final int DIALOG_FOREIGN_EXCHANGE_PROGRESS = 0;
    private ProgressDialog progressDialog;

    public ExchangeRatesRetriever(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Foreign Exchange Rates");
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
    protected JSONObject doInBackground(String... strings) {
        Log.e(FOREIGN_EXCHANGE_LOG_TAG, "DO IN BACKGROUND FOREIGN_EXCHANGE");
        try {
            String response = Jsoup.connect(strings[0])
                    .ignoreContentType(true) // To Get JSON DATA
                    .get()
                    .text();

            jsonObject = new JSONObject(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        Log.e(FOREIGN_EXCHANGE_LOG_TAG,"Foreign Exchange Rates:");
        Log.e(FOREIGN_EXCHANGE_LOG_TAG,jsonObject.toString());
        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        Log.e(FOREIGN_EXCHANGE_LOG_TAG, "ON POST EXECUTE FOREIGN_EXCHANGE");
        JSONObject jsonRates = null;
        try {
            jsonRates = jsonObject.getJSONObject("rates");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < GlobalVariables.CountryCodes.length; i++) {
            try {
                GlobalVariables.CountryRates[i] = jsonRates.getDouble(GlobalVariables.CountryCodes[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        progressDialog.dismiss();
    }

}
