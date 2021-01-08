package com.finance.portfollio.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseUtils {

    //TODO Caner - DONE TO BE CHECKED - Create 4 X 3 = 12 CRUD methods for all 3 Tables (Create Retrieve Update Delete)





    // START Of AssetInfoTable


    public boolean InsertAssetInfoTableEntry(String asset_code, int asset_category, SQLiteDatabase db) {
        long result = -1;
        try
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DB.AssetInfoTable.COL_0_ASSET_CODE, asset_code);
            contentValues.put(DB.AssetInfoTable.COL_1_ASSET_CATEGORY, asset_category);

            result = db.insert(DB.AssetInfoTable.TABLE_NAME, null, contentValues);
        }
        catch (Exception ex)
        {
            //Log.e("messageCategory", "DatabaseUtils AssetInfoTable EXCEPTION: "+ex.getMessage());
        }
        if(result == -1)
            return false;
        else
            return true;
    }


    public static String RetrieveAssetCategoryByAssetCode(String asset_code, SQLiteDatabase db)
    {
        String result = "";
        String whereclause = DB.AssetInfoTable.COL_0_ASSET_CODE + "=?";
        String[] whereargs = new String[]{asset_code};
        Cursor csr = db.query(DB.AssetInfoTable.TABLE_NAME,null,whereclause,whereargs,null,null,null);
        if (csr.moveToFirst()) {
            result = csr.getString(csr.getColumnIndex(DB.AssetInfoTable.COL_1_ASSET_CATEGORY));
        }
        return result;
    }


    // END Of AssetInfoTable



    // START Of AssetPortfolioTable

    public boolean InsertAssetPortfolioTableEntry(String asset_code, double asset_quantity, SQLiteDatabase db) {
        long result = -1;
        try
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DB.AssetPortfolioTable.COL_0_ASSET_CODE, asset_code);
            contentValues.put(DB.AssetPortfolioTable.COL_1_ASSET_QUANTITY, asset_quantity);

            result = db.insert(DB.AssetPortfolioTable.TABLE_NAME, null, contentValues);
        }
        catch (Exception ex)
        {
            //Log.e("messageCategory", "DatabaseUtils AssetPortfolioTable EXCEPTION: "+ex.getMessage());
        }
        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean isAssetEntryExistentOnPortfolio(String asset_code, Integer defaultValue, SQLiteDatabase db)
    {
        try
        {
            String whereclause = DB.AssetPortfolioTable.COL_0_ASSET_CODE + "=?";
            String[] whereargs = new String[]{String.valueOf(asset_code)};
            Cursor cursor = db.query(DB.AssetPortfolioTable.TABLE_NAME,null,whereclause,whereargs,null,null,null);
            if(cursor.getCount() <= 0) // if the asset entry not existent on the portfolio, attempts to insert it
            {
                cursor.close();
                boolean isEmptyInsertedSuccessfully = false;
                if(defaultValue != -1)
                {
                    isEmptyInsertedSuccessfully = InsertAssetPortfolioTableEntry(asset_code, defaultValue, db);
                }
                return isEmptyInsertedSuccessfully;
            }
            cursor.close();
            return true;
        }
        catch(Exception ex)
        {
            //Log.e("messageCategory", "DatabaseUtils isAssetEntryExistentOnPortfolio EXCEPTION: "+ex.getMessage());
            return false;
        }
    }



    public double RetrieveAssetQuantityOnPortfolioByAssetCode(String asset_code, SQLiteDatabase db)
    {
        double result = -1.0;
        String whereclause = DB.AssetPortfolioTable.COL_0_ASSET_CODE + "=?";
        String[] whereargs = new String[]{asset_code};
        Cursor csr = db.query(DB.AssetPortfolioTable.TABLE_NAME,null,whereclause,whereargs,null,null,null);
        if (csr.moveToFirst()) {
            result = csr.getDouble(csr.getColumnIndex(DB.AssetPortfolioTable.COL_1_ASSET_QUANTITY));
        }
        return result;
    }


    public boolean UpdateAssetQuantityOnPortfolioByAssetCode(String asset_code, double new_asset_quantity, SQLiteDatabase db) {
        int result = -1;
        try
        {
            //Log.i("messageCategory", "DatabaseHelper Update DataTableGlobalVariables entered: "); // delete afterwards
            ContentValues contentValues = new ContentValues();
            contentValues.put(DB.AssetPortfolioTable.COL_1_ASSET_QUANTITY, new_asset_quantity);
            result = db.update(DB.AssetPortfolioTable.TABLE_NAME,contentValues ,DB.AssetPortfolioTable.COL_0_ASSET_CODE + "= ?", new String[] {""+asset_code});
        }
        catch (Exception ex)
        {
            //Log.e("messageCategory", "DatabaseUtils Update AssetQuantityOnPortfolioByAssetCode EXCEPTION: "+ex.getMessage());
        }
        if(result == -1)
            return false;
        else
            return true;
    }

    // END Of AssetPortfolioTable





    // START Of StockTransactionsTable

    public boolean InsertStockTransactionsTableEntry(String asset_code, double transaction_quantity, double transaction_unit_price, GlobalVariables.TRANSACTION_TYPE transaction_type, int transaction_timestamp, SQLiteDatabase db) {
        long result = -1;
        try
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DB.StockTransactionsTable.COL_1_ASSET_CODE, asset_code);
            contentValues.put(DB.StockTransactionsTable.COL_2_TRANSACTION_QUANTITY, transaction_quantity);
            contentValues.put(DB.StockTransactionsTable.COL_3_TRANSACTION_UNIT_PRICE, transaction_unit_price);
            contentValues.put(DB.StockTransactionsTable.COL_4_TRANSACTION_TYPE, transaction_type.getTransactionType());
            contentValues.put(DB.StockTransactionsTable.COL_5_TRANSACTION_TIMESTAMP, transaction_timestamp);

            result = db.insert(DB.StockTransactionsTable.TABLE_NAME, null, contentValues);
        }
        catch (Exception ex)
        {
            //Log.e("messageCategory", "DatabaseUtils StockTransactionsTable EXCEPTION: "+ex.getMessage());
        }
        if(result == -1)
            return false;
        else
            return true;
    }



    // END Of StockTransactionsTable



    // START Of CurrencyTransactionsTable



    public boolean InsertCurrencyTransactionsTableEntry(String currency_code_to_be_sold, String currency_code_to_be_bought, double transaction_quantity, double transaction_unit_price, GlobalVariables.TRANSACTION_TYPE transaction_type, int transaction_timestamp, SQLiteDatabase db) {
        long result = -1;
        try
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DB.CurrencyTransactionsTable.COL_1_CURRENCY_CODE_TO_BE_SOLD, currency_code_to_be_sold);
            contentValues.put(DB.CurrencyTransactionsTable.COL_2_CURRENCY_CODE_TO_BE_BOUGHT, currency_code_to_be_bought);
            contentValues.put(DB.CurrencyTransactionsTable.COL_3_TRANSACTION_QUANTITY, transaction_quantity);
            contentValues.put(DB.CurrencyTransactionsTable.COL_4_TRANSACTION_UNIT_PRICE, transaction_unit_price);
            contentValues.put(DB.CurrencyTransactionsTable.COL_5_TRANSACTION_TYPE, transaction_type.getTransactionType());
            contentValues.put(DB.CurrencyTransactionsTable.COL_6_TRANSACTION_TIMESTAMP, transaction_timestamp);

            result = db.insert(DB.CurrencyTransactionsTable.TABLE_NAME, null, contentValues);
        }
        catch (Exception ex)
        {
            //Log.e("messageCategory", "DatabaseUtils CurrencyTransactionsTable EXCEPTION: "+ex.getMessage());
        }
        if(result == -1)
            return false;
        else
            return true;
    }


    // END Of CurrencyTransactionsTable


}
