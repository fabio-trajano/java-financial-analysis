package com.fabio_trajano.java_financial_analysis.dto;

public record EarningsResponseDTO(String date,
                                  String symbol,
                                  String actualEarningResult,
                                  String estimatedEarning) {
}
