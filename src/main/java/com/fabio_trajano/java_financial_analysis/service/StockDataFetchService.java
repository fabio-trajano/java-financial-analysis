package com.fabio_trajano.java_financial_analysis.service;


import com.fabio_trajano.java_financial_analysis.dto.DividendsResponseDTO;
import com.fabio_trajano.java_financial_analysis.dto.EarningsResponseDTO;
import com.fabio_trajano.java_financial_analysis.dto.IncomeResponseDTO;
import com.fabio_trajano.java_financial_analysis.dto.StockResponseDTO;
import com.fabio_trajano.java_financial_analysis.model.TickerRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class StockDataFetchService {

    @Value("${fmp.apikey}")
    private String apiKey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public StockDataFetchService(WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    public Double price(TickerRequest ticker) {

        String priceUrl = "https://financialmodelingprep.com/api/v3/quote-short/" + ticker.getTicker() + "?apikey=" + apiKey;

        Mono<String> responseMono = webClient.get()
                .uri(priceUrl)
                .retrieve()
                .bodyToMono(String.class);

        String response = responseMono.block();

        try {
            StockResponseDTO[] stockResponseDTOs = objectMapper.readValue(response, StockResponseDTO[].class);
            return stockResponseDTOs[0].price();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Double earningsPerShare(TickerRequest ticker) {

        String earningsUrl = "https://financialmodelingprep.com/api/v3/earnings-surprises/" + ticker.getTicker() + "?apikey=" +apiKey;

        Mono<String> responseMono = webClient.get()
                .uri(earningsUrl)
                .retrieve()
                .bodyToMono(String.class);

        String response = responseMono.block();

        try {

            EarningsResponseDTO[] earningsResponseDTOS = objectMapper.readValue(response, EarningsResponseDTO[].class);

            LocalDate oneYearAgo = LocalDate.now().minusYears(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return Arrays.stream(earningsResponseDTOS)
                    .filter(dto -> LocalDate.parse(dto.date(), formatter).isAfter(oneYearAgo))
                    .mapToDouble(dto -> Double.parseDouble(dto.actualEarningResult()))
                    .sum();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Double annualDividend(TickerRequest ticker) {

        String earningsUrl = "https://financialmodelingprep.com/api/v3/historical-price-full/stock_dividend/" + ticker.getTicker() + "?apikey=" + apiKey;

        Mono<String> responseMono = webClient.get()
                .uri(earningsUrl)
                .retrieve()
                .bodyToMono(String.class);

        String response = responseMono.block();

        try {
            DividendsResponseDTO dividendsResponseDTO = objectMapper.readValue(response, DividendsResponseDTO.class);

            LocalDate oneYearAgo = LocalDate.now().minusYears(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            List<DividendsResponseDTO.DividendHistory> historical = dividendsResponseDTO.historical();

            return historical.stream()
                    .filter(history -> LocalDate.parse(history.date(), formatter).isAfter(oneYearAgo))
                    .mapToDouble(DividendsResponseDTO.DividendHistory::dividend)
                    .sum();


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public Long annualIncome(TickerRequest ticker) {

        String financialsUrl = "https://financialmodelingprep.com/api/v3/income-statement/" + ticker.getTicker() + "?period=annual&apikey=" + apiKey;

        Mono<String> responseMono = webClient.get()
                .uri(financialsUrl)
                .retrieve()
                .bodyToMono(String.class);

        String response = responseMono.block();

        try {
            IncomeResponseDTO[] income = objectMapper.readValue(response, IncomeResponseDTO[].class);
            return income[1].netIncome();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

