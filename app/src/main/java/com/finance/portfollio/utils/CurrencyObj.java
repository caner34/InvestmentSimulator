package com.finance.portfollio.utils;

public class CurrencyObj {

    String currencyCode;
    // the exchange value of the currency according to US Dollar
    double parity;
    double quantity;

    public CurrencyObj(String currencyCode, double quantity) {
        this.currencyCode = currencyCode;
        this.quantity = quantity;
        // TODO - Taha get parity on the following line using GetConvertedRate method in AsyncTaskHelper class
        this.parity = 0;
    }
}