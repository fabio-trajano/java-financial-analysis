package com.fabio_trajano.java_financial_analysis.service;

import com.fabio_trajano.java_financial_analysis.model.TickerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StockDataFetchServiceTest {

    @Autowired
    private StockDataFetchService stockDataFetchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPrice_validTicker_returnsPrice() throws Exception {
        String ticker = "AAPL";
        TickerRequest tickerRequest = new TickerRequest(ticker);

        Double price = stockDataFetchService.price(tickerRequest);
        System.out.println("Price: " + price.toString());

        assertNotNull(price, "Price should not be null");
        assertNotEquals(0.0, "Price should be different from zero");
    }

    @Test
    public void testEarningsPerShare_validTicker_returnsEarnings() throws Exception {
        String ticker = "AAPL";
        TickerRequest tickerRequest = new TickerRequest(ticker);

        Double earnings = stockDataFetchService.earningsPerShare(tickerRequest);
        System.out.println("Earnings in the last year: " + earnings.toString());

        assertNotNull(earnings, "Earnings should not be null");
    }

    @Test
    public void testDividens_validTicker_returnsDividends() throws Exception {
        String ticker = "AAPL";
        TickerRequest tickerRequest = new TickerRequest(ticker);

        Double dividends = stockDataFetchService.annualDividend(tickerRequest);
        System.out.println("Dividends in the last year: " + dividends.toString());

        assertNotNull(dividends, "Earnings should not be null");
    }

}
