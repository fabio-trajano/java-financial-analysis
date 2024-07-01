package com.fabio_trajano.java_financial_analysis.controller;

import com.fabio_trajano.java_financial_analysis.dto.StockResponseDTO;
import com.fabio_trajano.java_financial_analysis.model.StockData;
import com.fabio_trajano.java_financial_analysis.model.TickerRequest;
import com.fabio_trajano.java_financial_analysis.service.StockDataFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockDataFetchService stockService;
    private StockData stock;

    @GetMapping("/api/stock/{ticker}")
    public StockResponseDTO getStockData(@PathVariable String ticker) {
        TickerRequest tickerRequest = new TickerRequest(ticker);
        return new StockResponseDTO(ticker, stockService.price(tickerRequest));
    }
}

