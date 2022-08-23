package com.grokthecode.tutorials.mockito.stocks;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTutorial {

    // https://www.tutorialspoint.com/mockito/index.htm
    public static void main(final String[] args) {
        //Create a portfolio object which is to be tested.
        final Portfolio portfolio = new Portfolio();

        //Create a list of stocks to be added to the portfolio
        final List<Stock> stocks = new ArrayList<>();
        final Stock googleStock = new Stock("1", "Google", 10);
        final Stock microsoftStock = new Stock("2", "Microsoft", 100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);

        //Create the mock object of the stock service
        final StockService stockServiceMock = mock(StockService.class);

        //Mock teh behavior of stock service to return the value of various stocks
        when(stockServiceMock.getPrice(googleStock)).thenReturn(50.00);
        when(stockServiceMock.getPrice(microsoftStock)).thenReturn(1000.00);

        //Add stocks to the portfolio
        portfolio.setStocks(stocks);
        portfolio.setStockService(stockServiceMock);

        double marketValue = portfolio.getMarketValue();

        //Verify the market value to be
        //10*50.00 + 100* 1000.00 = 500.00 + 100000.00 = 100500
        System.out.println("Market value of Portfolio: " + marketValue);
    }
}
