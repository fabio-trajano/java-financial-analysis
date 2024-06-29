package com.fabio_trajano.java_financial_analysis.DTO;

public record EarningsResponseDTO(String date,
                                  String symbol,
                                  String actualEarningResult,
                                  String estimatedEarning) {
}
