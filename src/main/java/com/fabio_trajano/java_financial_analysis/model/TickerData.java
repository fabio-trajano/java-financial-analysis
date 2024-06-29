package com.fabio_trajano.java_financial_analysis.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TickerData {
    private final String ticker;

    private final Double price;
    private final Double earningsPerShare;
    private final Double annualDividend;
    private final Double netIncome;
    private final Double shareholdersEquity;
    private final Map<String, Double> historicalPriceMap;
    private final List<Double> historicalPriceList;
    private final Double historicalPricePeriod;
}
