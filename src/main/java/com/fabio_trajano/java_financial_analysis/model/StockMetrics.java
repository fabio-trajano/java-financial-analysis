package com.fabio_trajano.java_financial_analysis.model;

public class StockMetrics {

    private final double revenuePerShare;
    private final double netIncomePerShare;
    private final double shareholdersEquityPerShare;
    private final double roe;
    private final double incomeQuality;
    private final double dividendYield;
    private final double peRatio;
    private final double earningsYield;
    private final double roic;

    public StockMetrics(double revenuePerShare, double netIncomePerShare, double shareholdersEquityPerShare, double roe, double incomeQuality, double dividendYield, double peRatio, double earningsYield, double roic) {
        this.revenuePerShare = revenuePerShare;
        this.netIncomePerShare = netIncomePerShare;
        this.shareholdersEquityPerShare = shareholdersEquityPerShare;
        this.roe = roe;
        this.incomeQuality = incomeQuality;
        this.dividendYield = dividendYield;
        this.peRatio = peRatio;
        this.earningsYield = earningsYield;
        this.roic = roic;
    }
}
