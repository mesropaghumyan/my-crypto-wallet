package fr.mycryptowallet.infrastructure.driving.mapper;

import fr.mycryptowallet.domain.Investment;
import fr.mycryptowallet.infrastructure.driving.dto.WalletItemDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class InvestmentMapper {

    public Investment toInvestment(WalletItemDTO dto) {
        return Investment.builder()
                .cryptoName(dto.crypto())
                .quantity(dto.quantity())
                .build();
    }

    public List<Investment> toInvestmentList(List<WalletItemDTO> dtos) {
        return dtos.stream()
                .map(this::toInvestment)
                .collect(Collectors.toList());
    }
}
