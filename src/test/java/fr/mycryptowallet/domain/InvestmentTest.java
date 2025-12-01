package fr.mycryptowallet.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvestmentTest {
    @Test
    void valueTest() {
        String randomCryptoName = UUID.randomUUID().toString();
        double randomQuantity = Math.random() * 10.0; // Nombre entre 0 et 10
        double randomMarketPrice = Math.random() * 50000.0; // Prix entre 0 et 50000
        Investment investment = Investment.builder()
                .cryptoName(randomCryptoName)
                .quantity(randomQuantity)
                .build();

        double actualValue = investment.value(randomMarketPrice);

        double expectedValue = randomQuantity * randomMarketPrice;
        assertEquals(expectedValue, actualValue, 0.0001); // Delta de 0.0001 car ce sont des doubles
    }
}
