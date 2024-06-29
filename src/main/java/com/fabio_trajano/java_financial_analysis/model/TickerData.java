package com.fabio_trajano.java_financial_analysis.model;


public record TickerData(String ticker,
                         Double price,
                         Double earningsPerShare,
                         Double annualDividend,
                         Double netIncome,
                         Double shareholdersEquity,
                         Double historicalPricePeriod) {
}
