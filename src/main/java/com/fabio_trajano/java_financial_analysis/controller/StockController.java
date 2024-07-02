package com.fabio_trajano.java_financial_analysis.controller;

import com.fabio_trajano.java_financial_analysis.dto.IncomeResponseDTO;
import com.fabio_trajano.java_financial_analysis.dto.MetricsDTO;
import com.fabio_trajano.java_financial_analysis.dto.StockResponseDTO;
import com.fabio_trajano.java_financial_analysis.model.StockData;
import com.fabio_trajano.java_financial_analysis.model.StockMetrics;
import com.fabio_trajano.java_financial_analysis.model.TickerRequest;
import com.fabio_trajano.java_financial_analysis.service.StockDataFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockDataFetchService stockService;
    private StockData stock;

    @GetMapping("/{ticker}")
    public StockData getStockData(@PathVariable String ticker) {
        TickerRequest tickerRequest = new TickerRequest(ticker);

        StockResponseDTO stockInfo = stockService.stockPrice(tickerRequest);
        IncomeResponseDTO incomeResponse = stockService.annualIncome(tickerRequest);
        Double earnings = stockService.earningsPerShare(tickerRequest);
        Double dividends = stockService.annualDividend(tickerRequest);

        return new StockData(
                stockInfo.symbol(),
                incomeResponse.calendarYear(),
                stockInfo.price(),
                incomeResponse.revenue(),
                incomeResponse.netIncome(),
                earnings,
                null
        );
    }

    @GetMapping("/{ticker}/metrics")
    public StockMetrics getStockMetrics(@PathVariable String ticker) {
        TickerRequest tickerRequest = new TickerRequest(ticker);
        MetricsDTO metricsDTO = stockService.metricsDTO(tickerRequest);
        return new StockMetrics(
                metricsDTO.revenuePerShare(),
                metricsDTO.netIncomePerShare(),
                metricsDTO.shareholdersEquityPerShare(),
                metricsDTO.roe(),
                metricsDTO.incomeQuality(),
                metricsDTO.dividendYield(),
                metricsDTO.peRatio(),
                metricsDTO.earningsYield(),
                metricsDTO.roic()
        );
    }

    @GetMapping("/{ticker}/full")
    public StockData getFullStockData(@PathVariable String ticker) {
        StockData basicData = getStockData(ticker);
        StockMetrics metrics = getStockMetrics(ticker);

        return new StockData(
                basicData.symbol(),
                basicData.calendarYear(),
                basicData.price(),
                basicData.revenue(),
                basicData.netIncome(),
                basicData.earning(),
                metrics
        );
    }
}

