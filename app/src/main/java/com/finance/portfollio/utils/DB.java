package com.finance.portfollio.utils;

import android.provider.BaseColumns;

public class DB {

    /*
        DEAR ALL including ME :)
        PLEASE DO NOT FORGET TO INCREMENT THE DATABASE_VERSION IN CASE THE DB SCHEMA IS ALTERED
    */

    public static abstract class AssetInfoTable implements BaseColumns
    {
        public static final String TABLE_NAME = "ASSET_INFO";
        public static final String COL_0_ASSET_CODE = "asset_code";
        public static final String COL_1_ASSET_CATEGORY = "asset_category";
    }

    public static abstract class AssetPortfolioTable implements BaseColumns
    {
        public static final String TABLE_NAME = "ASSET_PORTFOLIO";
        public static final String COL_0_ASSET_CODE = "asset_code";
        public static final String COL_1_ASSET_QUANTITY = "asset_quantity";
    }


    public static abstract class StockTransactionsTable implements BaseColumns
    {
        public static final String TABLE_NAME = "STOCK_TRANSACTIONS";
        public static final String COL_0_TRANSACTION_ID = "transaction_id";
        public static final String COL_1_ASSET_CODE = "asset_code";
        public static final String COL_2_TRANSACTION_QUANTITY = "transaction_quantity";
        public static final String COL_3_TRANSACTION_UNIT_PRICE = "transaction_unit_price";
        public static final String COL_4_TRANSACTION_TYPE = "transaction_type";
        public static final String COL_5_TRANSACTION_TIMESTAMP = "transaction_timestamp";
    }

    public static abstract class CurrencyTransactionsTable implements BaseColumns
    {
        public static final String TABLE_NAME = "CURRENCY_TRANSACTIONS";
        public static final String COL_0_TRANSACTION_ID = "transaction_id";
        public static final String COL_1_CURRENCY_CODE_TO_BE_SOLD = "currency_code_to_be_sold";
        public static final String COL_2_CURRENCY_CODE_TO_BE_BOUGHT = "currency_code_to_be_bought";
        public static final String COL_3_TRANSACTION_QUANTITY = "transaction_quantity";
        public static final String COL_4_TRANSACTION_UNIT_PRICE = "transaction_unit_price";
        public static final String COL_5_TRANSACTION_TYPE = "transaction_type";
        public static final String COL_6_TRANSACTION_TIMESTAMP = "transaction_timestamp";
    }






}
