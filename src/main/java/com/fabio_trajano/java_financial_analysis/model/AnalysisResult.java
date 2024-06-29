package com.fabio_trajano.java_financial_analysis.model;


public record AnalysisResult(String ticker,
                             Double peRatio,
                             Double dividendYield,
                             Double roe,
                             Double debtToEquityRatio,
                             Double sma50,
                             Double rsi14,
                             Double macd) {
}
