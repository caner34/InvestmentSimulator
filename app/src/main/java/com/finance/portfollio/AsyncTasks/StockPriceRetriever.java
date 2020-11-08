package com.finance.portfollio.AsyncTasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.finance.portfollio.utils.StockObj;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class StockPriceRetriever extends AsyncTask<String, Void, String> {

    String KEY_ENCODING_OUTPUT = "UTF-8";
    String KEY_ENCODING_INPUT = "UTF-8";


    Activity activity;
    Context context;
    String stockCode;
    TextView textViewPrice;


    String resultString;


    public StockPriceRetriever(Activity activity, Context context, String stockCode, TextView textViewPrice) {
        this.activity = activity;
        this.context = context;
        this.stockCode =  stockCode;
        this.textViewPrice = textViewPrice;
    }


    @Override
    protected String doInBackground(String... params) {
        String type = params[0];

        resultString = Execute_BooleanQuery(type, params);
        return resultString;
    }

    private String Execute_BooleanQuery(String type, String... params) {
        try
        {
            StockObj stock = new StockObj(stockCode);
            resultString = stock.toString();

            return resultString;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            resultString = "Exception " + e.getMessage();
            return resultString;
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    static String GetPostDataFromParams(String encoding, String... params) {
        StringBuilder sb = new StringBuilder();
        String crLine = "";


        for (int i = 0; i < ((params.length - 1) / 2); i++) {
            crLine = "";
            try {
                if (i != 0) {
                    crLine = "&";
                }
                crLine += URLEncoder.encode(params[1 + 2 * i], encoding) + "=" + URLEncoder.encode(params[1 + 2 * i + 1], encoding);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "failedPostData";
            }

            sb.append(crLine);
        }

        return sb.toString();
    }

    @Override
    protected void onPreExecute() {


    }

    @Override
    protected void onPostExecute(String result) {

        textViewPrice.setText(resultString);
    }
}