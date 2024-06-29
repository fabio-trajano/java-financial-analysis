package com.fabio_trajano.java_financial_analysis.service;


import com.fabio_trajano.java_financial_analysis.analysis.FundamentalAnalysis;
import com.fabio_trajano.java_financial_analysis.analysis.TechnicalAnalysis;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialAnalysisService {

    private final FundamentalAnalysis fundamentalAnalysis;
    private final TechnicalAnalysis technicalAnalysis;

    public FinancialAnalysisService() {
        this.fundamentalAnalysis = new FundamentalAnalysis();
        this.technicalAnalysis = new TechnicalAnalysis();
    }

    public double getPE(double price, double earningsPerShare) {
        return fundamentalAnalysis.calculatePE(price, earningsPerShare);
    }

    public double getDividendYield(double annualDividend, double price) {
        return fundamentalAnalysis.calculateDividendYield(annualDividend, price);
    }

    public double getROE(double netIncome, double shareholdersEquity) {
        return fundamentalAnalysis.calculateROE(netIncome, shareholdersEquity);
    }

    public double getSMA(List<Double> prices, int period) {
        return technicalAnalysis.calculateSMA(prices, period);
    }

    public double getRSI(List<Double> prices, int period) {
        return technicalAnalysis.calculateRSI(prices, period);
    }

    public double getMACD(List<Double> prices, int shortPeriod, int longPeriod, int signalPeriod) {
        return technicalAnalysis.calculateMACD(prices, shortPeriod, longPeriod, signalPeriod);
    }
}

