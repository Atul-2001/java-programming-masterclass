package com.signature.stockclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientStockClientIntegrationTest {

    private WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveStockPricesFromTheServices() {
        //given
        StockClient stockClient = new WebClientStockClient(webClient);

        //when
        Flux<StockPrice> prices = stockClient.pricesFor("SYMBOL");

        //then
        Assertions.assertNotNull(prices);
        Flux<StockPrice> fivePrices = prices.take(5);
        Assertions.assertEquals(5, fivePrices.count().block());
        Assertions.assertEquals("SYMBOL", fivePrices.blockFirst().getSymbol());
//        Assertions.assertTrue(prices.take(5).count().block() > 0);
    }
}