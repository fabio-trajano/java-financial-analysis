package com.fabio_trajano.java_financial_analysis.DTO;

public record DividendsResponseDTO(String date,
                                   String label,
                                   Double adjDividend,
                                   Double dividend,
                                   String recordDate,
                                   String paymentDate,
                                   String DeclarationDate) {
}
