package com.fabio_trajano.java_financial_analysis.service;

import com.fabio_trajano.java_financial_analysis.dto.*;
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

    @Value("${fpm.baseurl}")
    private String baseUrl;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public StockDataFetchService(WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    public StockResponseDTO stockPrice(TickerRequest ticker) {
        String priceUrl = baseUrl + "quote-short/" + ticker.getTicker() + "?apikey=" + apiKey;
        String response = fetchData(priceUrl);
        try {
            StockResponseDTO[] stockResponseDTOs = objectMapper.readValue(response, StockResponseDTO[].class);
            return stockResponseDTOs[0];
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing stock price data", e);
        }
    }

    public Double earningsPerShare(TickerRequest ticker) {
        String earningsUrl = baseUrl + "earnings-surprises/" + ticker.getTicker() + "?apikey=" + apiKey;
        String response = fetchData(earningsUrl);
        try {
            EarningsResponseDTO[] earningsResponseDTOs = objectMapper.readValue(response, EarningsResponseDTO[].class);
            LocalDate oneYearAgo = LocalDate.now().minusYears(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return Arrays.stream(earningsResponseDTOs)
                    .filter(dto -> LocalDate.parse(dto.date(), formatter).isAfter(oneYearAgo))
                    .mapToDouble(dto -> Double.parseDouble(dto.actualEarningResult()))
                    .sum();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing earnings data", e);
        }
    }

    public Double annualDividend(TickerRequest ticker) {
        String dividendUrl = baseUrl + "historical-price-full/stock_dividend/" + ticker.getTicker() + "?apikey=" + apiKey;
        String response = fetchData(dividendUrl);
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
            throw new RuntimeException("Error processing dividend data", e);
        }
    }

    public IncomeResponseDTO annualIncome(TickerRequest ticker) {
        String financialsUrl = baseUrl + "income-statement/" + ticker.getTicker() + "?period=annual&apikey=" + apiKey;
        String response = fetchData(financialsUrl);
        try {
            IncomeResponseDTO[] income = objectMapper.readValue(response, IncomeResponseDTO[].class);
            return income[1];
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing income data", e);
        }
    }

    public MetricsDTO metricsDTO(TickerRequest ticker) {
        String metricsUrl = baseUrl + "key-metrics/" + ticker.getTicker() + "?period=annual&apikey=" + apiKey;
        String response = fetchData(metricsUrl);
        try {
            MetricsDTO[] metricsDTOArray = objectMapper.readValue(response, MetricsDTO[].class);
            if (metricsDTOArray.length > 0) {
                return metricsDTOArray[0];
            } else {
                throw new RuntimeException("No metrics data available for " + ticker.getTicker());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing metrics data", e);
        }
    }

    private String fetchData(String url) {
        System.out.println("Fetching data from: " + url);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
