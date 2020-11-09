package com.finance.portfollio.utils;

public class FinancialNewsElement {
    String source, title, link;

    public FinancialNewsElement(String source, String title, String link) {
        this.source = source;
        this.title = title;
        this.link = link;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

}
