package fr.mycryptowallet.infrastructure.driven;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoinGeckoAdapterTest {

    @Mock
    CoinGeckoClient coinGeckoClient;

    @InjectMocks
    CoinGeckoAdapter adapter;

    @Test
    void getPrice() {
        String randomCryptoName = UUID.randomUUID().toString();
        String cryptoId = randomCryptoName.toLowerCase();
        double randomPrice = Math.random() * 10000.0;
        Map<String, Double> currencyMap = Map.of("eur", randomPrice);
        Map<String, Map<String, Double>> apiResponse = Map.of(cryptoId, currencyMap);
        when(coinGeckoClient.getPrice(cryptoId, "eur")).thenReturn(apiResponse);

        Double result = adapter.getPrice(randomCryptoName);

        assertEquals(randomPrice, result);
        verify(coinGeckoClient).getPrice(cryptoId, "eur");
    }

    @Test
    void getPrice_whenCryptoNotFound() {
        String randomCryptoName = UUID.randomUUID().toString();
        when(coinGeckoClient.getPrice(anyString(), anyString())).thenReturn(Collections.emptyMap());

        Double result = adapter.getPrice(randomCryptoName);

        assertEquals(0.0, result);
    }

    @Test
    void getPrice_whenCurrencyNotFound() {
        String randomCryptoName = UUID.randomUUID().toString();
        String cryptoId = randomCryptoName.toLowerCase();
        String randomWrongCurrency = UUID.randomUUID().toString();
        Map<String, Double> wrongCurrencyMap = Map.of(randomWrongCurrency, 100.0);
        Map<String, Map<String, Double>> apiResponse = Map.of(cryptoId, wrongCurrencyMap);
        when(coinGeckoClient.getPrice(cryptoId, "eur")).thenReturn(apiResponse);

        Double result = adapter.getPrice(randomCryptoName);

        assertEquals(0.0, result);
    }

    @Test
    void getPrice_whenAPIThrownException() {
        String randomCryptoName = UUID.randomUUID().toString();
        when(coinGeckoClient.getPrice(anyString(), anyString()))
                .thenThrow(new RuntimeException("API CoinGecko unavailable"));

        Double result = adapter.getPrice(randomCryptoName);

        assertEquals(0.0, result);
    }
}