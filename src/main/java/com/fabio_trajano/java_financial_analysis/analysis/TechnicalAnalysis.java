package com.fabio_trajano.java_financial_analysis.analysis;

import java.util.List;

public class TechnicalAnalysis {

    // Method to calculate Simple Moving Average (SMA)
    public double calculateSMA(List<Double> prices, int period) {
        if (prices.size() < period) {
            throw new IllegalArgumentException("Not enough data points");
        }
        double sum = 0;
        for (int i = 0; i < period; i++) {
            sum += prices.get(i);
        }
        return sum / period;
    }

    // Method to calculate RSI (Relative Strength Index)
    public double calculateRSI(List<Double> prices, int period) {
        double gain = 0, loss = 0;
        for (int i = 1; i < period; i++) {
            double change = prices.get(i) - prices.get(i - 1);
            if (change > 0) {
                gain += change;
            } else {
                loss -= change;
            }
        }
        double rs = gain / loss;
        return 100 - (100 / (1 + rs));
    }

    // Method to calculate MACD (Moving Average Convergence Divergence)
    public double calculateMACD(List<Double> prices, int shortPeriod, int longPeriod, int signalPeriod) {
        double shortEMA = calculateEMA(prices, shortPeriod);
        double longEMA = calculateEMA(prices, longPeriod);
        return shortEMA - longEMA;
    }

    // Helper method to calculate Exponential Moving Average (EMA)
    public double calculateEMA(List<Double> prices, int period) {
        double multiplier = 2.0 / (period + 1);
        double ema = prices.get(0); // Initial EMA value is the first price
        for (int i = 1; i < prices.size(); i++) {
            ema = ((prices.get(i) - ema) * multiplier) + ema;
        }
        return ema;
    }
}
