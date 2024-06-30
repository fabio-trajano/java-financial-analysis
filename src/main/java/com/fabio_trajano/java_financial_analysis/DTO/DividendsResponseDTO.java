package com.fabio_trajano.java_financial_analysis.DTO;


import java.util.List;

public record DividendsResponseDTO(String symbol, List<DividendHistory> historical) {

    public record DividendHistory(
            String date,
            String label,
            Double adjDividend,
            Double dividend,
            String recordDate,
            String paymentDate,
            String declarationDate
    ) {}
}
