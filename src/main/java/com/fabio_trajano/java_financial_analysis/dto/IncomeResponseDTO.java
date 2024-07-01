package com.fabio_trajano.java_financial_analysis.dto;

public record IncomeResponseDTO(String symbol,
                                String calendarYear,
                                Long revenue,
                                Long netIncome) {
}
