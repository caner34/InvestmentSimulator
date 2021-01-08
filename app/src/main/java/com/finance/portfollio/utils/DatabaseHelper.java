package com.finance.portfollio.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    //TODO Caner - DONE TOBE CHECKED - Create DataBase
    //TODO Caner - DONE TOBE CHECKED - Database should include a table that contains the total assets of the user in terms of both Stocks and Currencies
    //TODO Caner - DONE TOBE CHECKED - Database should include 2 tables that contains the transactions of both Stocks and Currencies
    //TODO Caner - DONE TOBE CHECKED - Database should include a table that contains the Stocks and their types


    public static final String DATABASE_NAME = "GlobalDB.db";
    // PLEASE DO NOT FORGET TO INCREMENT THE DATABASE_VERSION IN CASE THE DB SCHEMA IS ALTERED
    public static final int DATABASE_VERSION = 2;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CreateAllTables(db);
        CreateAllVariablesEmpty(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DropAllTables(db);
        onCreate(db);
    }


    public void DropAllTables(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS "+DB.AssetInfoTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DB.AssetPortfolioTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DB.StockTransactionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DB.CurrencyTransactionsTable.TABLE_NAME);
    }

    public void CreateAllTables(SQLiteDatabase db)
    {
        db.execSQL("create table " + DB.AssetInfoTable.TABLE_NAME   + " (" + DB.AssetInfoTable.COL_0_ASSET_CODE   + " TEXT PRIMARY KEY," + DB.AssetInfoTable.COL_1_ASSET_CATEGORY + " TEXT)");
        db.execSQL("create table " + DB.AssetPortfolioTable.TABLE_NAME   + " (" + DB.AssetPortfolioTable.COL_0_ASSET_CODE   + " TEXT PRIMARY KEY," + DB.AssetPortfolioTable.COL_1_ASSET_QUANTITY + " REAL)");
        db.execSQL("create table " + DB.StockTransactionsTable.TABLE_NAME   + " (" + DB.StockTransactionsTable.COL_0_TRANSACTION_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ DB.StockTransactionsTable.COL_1_ASSET_CODE   + " TEXT," + DB.StockTransactionsTable.COL_2_TRANSACTION_QUANTITY + " REAL," + DB.StockTransactionsTable.COL_3_TRANSACTION_UNIT_PRICE + " REAL," + DB.StockTransactionsTable.COL_4_TRANSACTION_TYPE + " INTEGER," + DB.StockTransactionsTable.COL_5_TRANSACTION_TIMESTAMP + " INTEGER)");
        db.execSQL("create table " + DB.CurrencyTransactionsTable.TABLE_NAME   + " (" + DB.CurrencyTransactionsTable.COL_0_TRANSACTION_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT," + DB.CurrencyTransactionsTable.COL_1_CURRENCY_CODE_TO_BE_SOLD  + " TEXT," + DB.CurrencyTransactionsTable.COL_2_CURRENCY_CODE_TO_BE_BOUGHT   + " TEXT," + DB.CurrencyTransactionsTable.COL_3_TRANSACTION_QUANTITY + " REAL," + DB.CurrencyTransactionsTable.COL_4_TRANSACTION_UNIT_PRICE + " REAL," + DB.CurrencyTransactionsTable.COL_5_TRANSACTION_TYPE + " INTEGER," + DB.CurrencyTransactionsTable.COL_6_TRANSACTION_TIMESTAMP + " INTEGER)");
    }

    public void CreateAllVariablesEmpty(SQLiteDatabase db)
    {
        // For Our Prospective Anti-Addiction Counter-Measure Facility

        // String firstLunchDateTimeStr = "";

        // DateFormat dateFormat = new SimpleDateFormat("yyy-mm-dd hh:mm:ss");
        // Date crDate = Calendar.getInstance().getTime();
        // firstLunchDateTimeStr = dateFormat.format(crDate);
    }




}
