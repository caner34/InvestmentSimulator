package com.finance.portfollio.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils
{
    public static final String LOG_TAG = "general_message_log";

    public static void Log(String message)
    {
        Log.d(LOG_TAG, message);
    }

    public static String[] GetSpinnerStringArrayWithHeaderTitle(Spinner s, List<String> originalList, String additionalWarning)
    {

        List<String> result = new ArrayList<>();

        result.add(s.getPrompt().toString());
        
        if(!additionalWarning.equals(""))
        {
            result.add(additionalWarning);
        }

        for(int i = 0; i < originalList.size(); i++)
        {
            result.add(originalList.get(i));
        }
        return ((String[])result.toArray(new String[result.size()]));
    }

    public static int getIndexOfCurrencyCode(String currencyCode)
    {
        for(int i = 0; i < GlobalVariables.CountryCodes.length; i++)
        {
            if (GlobalVariables.CountryCodes[i].equals(currencyCode))
            {
                return i;
            }
        }
        return -1;
    }
}
