package com.fabio_trajano.java_financial_analysis.model;

import lombok.Data;

@Data
public class AnalysisResult {
    private final String ticker;
    private final Double peRatio;
    private final Double dividendYield;
    private final Double roe;
    private final Double debtToEquityRatio;
    private final Double sma50;
    private final Double rsi14;
    private final Double macd;
}
