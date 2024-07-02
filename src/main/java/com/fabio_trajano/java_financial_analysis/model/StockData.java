package com.fabio_trajano.java_financial_analysis.model;

public record StockData(
        String symbol,
        String calendarYear,
        double price,
        double revenue,
        double netIncome,
        double earning,
        StockMetrics metrics
)  {}