package com.fabio_trajano.java_financial_analysis.analysis;

public class FundamentalAnalysis {

    // Method to calculate P/E ratio
    public double calculatePE(double price, double earningsPerShare) {
        return price / earningsPerShare;
    }

    // Method to calculate Dividend Yield
    public double calculateDividendYield(double annualDividend, double price) {
        return (annualDividend / price) * 100;
    }

    // Method to calculate ROE
    public double calculateROE(double netIncome, double shareholdersEquity) {
        return (netIncome / shareholdersEquity) * 100;
    }

}
