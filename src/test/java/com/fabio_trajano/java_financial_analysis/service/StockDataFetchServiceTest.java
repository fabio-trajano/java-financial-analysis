package com.fabio_trajano.java_financial_analysis.service;

import com.fabio_trajano.java_financial_analysis.dto.IncomeResponseDTO;
import com.fabio_trajano.java_financial_analysis.dto.MetricsDTO;
import com.fabio_trajano.java_financial_analysis.dto.StockResponseDTO;
import com.fabio_trajano.java_financial_analysis.model.TickerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockDataFetchServiceTest {

    @Autowired
    private StockDataFetchService stockDataFetchService;

    private TickerRequest tickerRequest;

    @BeforeEach
    void setUp() {
        tickerRequest = new TickerRequest("AAPL");
    }

    @Test
    void testStockPrice_validTicker_returnsStockInfo() {
        StockResponseDTO stockInfo = stockDataFetchService.stockPrice(tickerRequest);

        assertNotNull(stockInfo, "Stock info should not be null");
        assertEquals("AAPL", stockInfo.symbol(), "Symbol should match the request");
        assertTrue(stockInfo.price() > 0, "Price should be greater than zero");

        System.out.println("Stock Price: " + stockInfo.price());
    }

    @Test
    void testEarningsPerShare_validTicker_returnsEarnings() {
        Double earnings = stockDataFetchService.earningsPerShare(tickerRequest);

        assertNotNull(earnings, "Earnings should not be null");
        assertTrue(earnings != 0, "Earnings should not be zero");

        System.out.println("Earnings in the last year: " + earnings);
    }

    @Test
    void testAnnualDividend_validTicker_returnsDividends() {
        Double dividends = stockDataFetchService.annualDividend(tickerRequest);

        assertNotNull(dividends, "Dividends should not be null");
        assertTrue(dividends >= 0, "Dividends should be non-negative");

        System.out.println("Dividends in the last year: " + dividends);
    }

    @Test
    void testAnnualIncome_validTicker_returnsIncomeData() {
        IncomeResponseDTO incomeResponse = stockDataFetchService.annualIncome(tickerRequest);

        assertNotNull(incomeResponse, "Income response should not be null");
        assertNotNull(incomeResponse.calendarYear(), "Calendar year should not be null");
        assertTrue(incomeResponse.revenue() > 0, "Revenue should be greater than zero");
        assertTrue(incomeResponse.netIncome() != 0, "Net income should not be zero");

        System.out.println("Annual Income - Year: " + incomeResponse.calendarYear() +
                ", Revenue: " + incomeResponse.revenue() +
                ", Net Income: " + incomeResponse.netIncome());
    }

    @Test
    void testMetricsDTO_validTicker_returnsMetrics() {
        MetricsDTO metricsDTO = stockDataFetchService.metricsDTO(tickerRequest);

        assertNotNull(metricsDTO, "Metrics DTO should not be null");
        assertTrue(metricsDTO.revenuePerShare() > 0, "Revenue per share should be greater than zero");
        assertTrue(metricsDTO.peRatio() > 0, "P/E ratio should be greater than zero");

        System.out.println("Metrics - Revenue per Share: " + metricsDTO.revenuePerShare() +
                ", P/E Ratio: " + metricsDTO.peRatio());
    }
}