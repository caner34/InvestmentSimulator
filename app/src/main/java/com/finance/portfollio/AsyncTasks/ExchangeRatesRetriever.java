package com.finance.portfollio.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.finance.portfollio.utils.GlobalVariables;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            jsonObject = readStream(in);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        System.out.println("Foreign Exchange Rates:");
        System.out.println(jsonObject.toString());
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

    private JSONObject readStream(InputStream is) throws JSONException {
        JSONObject jsonObject;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            jsonObject = new JSONObject(bo.toString());
            return jsonObject;
        } catch (Exception e) {
            return new JSONObject("Error");
        }
    }


}
