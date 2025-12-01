package fr.mycryptowallet.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.mycryptowallet.domain.CryptoPriceProvider;
import fr.mycryptowallet.domain.Investment;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {
    @Mock
    CryptoPriceProvider priceProvider;

    @InjectMocks
    WalletService walletService;

    @Test
    void totalValueTest() {
        String cryptoName1 = UUID.randomUUID().toString();
        double quantity1 = Math.random() * 10.0;
        double price1 = Math.random() * 50000.0;
        String cryptoName2 = UUID.randomUUID().toString();
        double quantity2 = Math.random() * 10.0;
        double price2 = Math.random() * 50000.0;
        when(priceProvider.getPrice(cryptoName1)).thenReturn(price1);
        when(priceProvider.getPrice(cryptoName2)).thenReturn(price2);
        Investment inv1 = Investment.builder()
                .cryptoName(cryptoName1)
                .quantity(quantity1)
                .build();
        Investment inv2 = Investment.builder()
                .cryptoName(cryptoName2)
                .quantity(quantity2)
                .build();
        List<Investment> myInvestments = List.of(inv1, inv2);

        double totalValue = walletService.totalValue(myInvestments);

        double expectedValue = (quantity1 * price1) + (quantity2 * price2);
        assertEquals(expectedValue, totalValue, 0.0001);
    }

    @Test
    void totalValueTest_whenPriceIsNull() {
        String cryptoName1 = UUID.randomUUID().toString();
        double quantity1 = Math.random() * 10.0;
        double price1 = Math.random() * 50000.0;
        String cryptoName2 = UUID.randomUUID().toString();
        double quantity2 = Math.random() * 10.0;
        when(priceProvider.getPrice(cryptoName1)).thenReturn(price1);
        when(priceProvider.getPrice(cryptoName2)).thenReturn(null);
        Investment inv1 = Investment.builder()
                .cryptoName(cryptoName1)
                .quantity(quantity1)
                .build();
        Investment inv2 = Investment.builder()
                .cryptoName(cryptoName2)
                .quantity(quantity2)
                .build();
        List<Investment> myInvestments = List.of(inv1, inv2);

        double totalValue = walletService.totalValue(myInvestments);

        double expectedValue = quantity1 * price1;
        assertEquals(expectedValue, totalValue, 0.0001);
    }
}
