package com.finance.portfollio.utils;

import android.content.Context;

import com.finance.portfollio.AsyncTasks.ExchangeRatesRetriever;

public class FinancialUtils {

    public static void calculateNetWorth(Context context)
    {
        new ExchangeRatesRetriever(context).execute("https://api.exchangeratesapi.io/latest");
        //TODO - Taha returns the net worth of the user
    }

}
