package com.fabio_trajano.java_financial_analysis.analysis;

public class FundamentalAnalysis {

    public double calculatePE(double price, double earningsPerShare) {
        return price / earningsPerShare;
    }
    public double calculateDividendYield(double annualDividend, double price) {
        return (annualDividend / price) * 100;
    }

    public double calculateROE(double netIncome, double shareholdersEquity) {
        return (netIncome / shareholdersEquity) * 100;
    }

}
