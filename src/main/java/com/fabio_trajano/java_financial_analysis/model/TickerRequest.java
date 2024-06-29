package com.fabio_trajano.java_financial_analysis.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class TickerRequest {

    private final String ticker;
    private final String startDate;
    private final String endDate;
    private final Integer monthsToSubtract;

    private static final DateTimeFormatter URL_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // User chooses how many months to subtract from today
    public TickerRequest(String ticker, Integer monthsToSubtract) {
        this.monthsToSubtract = monthsToSubtract;
        this.ticker = ticker.toUpperCase();
        this.startDate = LocalDate.now().minusYears(monthsToSubtract/12).toString();
        this.endDate = LocalDate.now().toString();

    }

    // 1 Year default
    public TickerRequest(String ticker) {
        this.monthsToSubtract = 12;
        this.ticker = ticker.toUpperCase();
        this.startDate = LocalDate.now().minusYears(monthsToSubtract/12).toString();
        this.endDate = LocalDate.now().toString();
    }

}

