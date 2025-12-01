package fr.mycryptowallet.infrastructure.driving;

import fr.mycryptowallet.application.WalletService;
import fr.mycryptowallet.domain.Investment;
import fr.mycryptowallet.infrastructure.driving.dto.WalletItemDTO;
import fr.mycryptowallet.infrastructure.driving.mapper.InvestmentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WalletResourceTest {

    @Mock
    WalletService walletService;

    @Spy
    InvestmentMapper investmentMapper;

    @InjectMocks
    WalletResource walletResource;

    @Test
    void calculateWalletValue() {
        String cryptoName = UUID.randomUUID().toString();
        double quantity = Math.random() * 10.0;
        WalletItemDTO dto = new WalletItemDTO(cryptoName, quantity);
        List<WalletItemDTO> inputDtos = List.of(dto);

        walletResource.calculateWalletValue(inputDtos);
        ArgumentCaptor<List<Investment>> captor = ArgumentCaptor.forClass(List.class);
        verify(walletService).totalValue(captor.capture());

        assertEquals(1, captor.getValue().size());
        assertEquals(cryptoName, captor.getValue().get(0).getCryptoName());
        assertEquals(quantity, captor.getValue().get(0).getQuantity());
    }
}
