package com.finance.portfollio.utils;

import java.io.IOException;
import java.math.BigDecimal;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockObj {

    BigDecimal price, change, peg, dividend;
    Stock stock;

    public StockObj(String stockCode)
    {

        Stock stock = null;
        try
        {
            stock = YahooFinance.get(stockCode);
            this.price = stock.getQuote().getPrice();
            this.change = stock.getQuote().getChangeInPercent();
            this.peg = stock.getStats().getPeg();
            this.dividend = stock.getDividend().getAnnualYieldPercent();
            this.stock = stock;
        }
        catch (IOException e)
        {
            this.price = null;
            this.change = null;
            this.peg = null;
            this.dividend = null;
        }
    }

    @Override
    public String toString() {
        String result = "";

        /*result = "StockObj{" +
                "price=" + price +
                ", change=" + change +
                ", peg=" + peg +
                ", dividend=" + dividend +
                '}';*/

        result = stock.getSymbol()
                + "\n"+ stock.getName()
                + "\nAsk: " + stock.getQuote().getAsk() + " " + stock.getCurrency()
                + "\nBid: " + stock.getQuote().getBid() + " " + stock.getCurrency()
                + "\nChange: " + stock.getQuote().getChange() + " " + stock.getCurrency()
                + "\nAvg Volume: " + stock.getQuote().getAvgVolume()
                + "\nVolume: " + stock.getQuote().getVolume()
                + "\nChange In Percent: " + stock.getQuote().getChangeInPercent()
                + "\nPrevious Close: " + stock.getQuote().getPreviousClose();
                // + "\nStats: " + stock.getStats()
                // + "\nDividend: " + stock.getDividend();

        return result;
    }

}
