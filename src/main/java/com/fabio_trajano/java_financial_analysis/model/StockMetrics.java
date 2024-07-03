package com.fabio_trajano.java_financial_analysis.model;

import lombok.Data;

@Data
public class StockMetrics {

    private double revenuePerShare;
    private double netIncomePerShare;
    private double shareholdersEquityPerShare;
    private double roe;
    private double incomeQuality;
    private double dividendYield;
    private double peRatio;
    private double earningsYield;
    private double roic;

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

    public StockMetrics() {

    }
}
