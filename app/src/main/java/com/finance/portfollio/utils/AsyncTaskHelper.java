package com.finance.portfollio.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.portfollio.AsyncTasks.StockPriceRetriever;

public class AsyncTaskHelper {

    public static void DisplayStockPriceOnTextView(Activity activity, Context context, String stockCode, TextView textViewPrice)
    {
        StockPriceRetriever worker
                = new StockPriceRetriever(activity, context, stockCode, textViewPrice);
        try
        {
            worker.execute("");
        }
        catch (Exception e)
        {
            CommonUtils.Log(e.getMessage());
        }
    }

}
