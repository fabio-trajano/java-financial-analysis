package com.fabio_trajano.java_financial_analysis.dto;

public record MetricsDTO(
        double revenuePerShare,
        double netIncomePerShare,
        double shareholdersEquityPerShare,
        double roe,
        double incomeQuality,
        double dividendYield,
        double peRatio,
        double earningsYield,
        double roic
) {}
