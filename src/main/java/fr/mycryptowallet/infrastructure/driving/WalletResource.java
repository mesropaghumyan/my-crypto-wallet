package fr.mycryptowallet.infrastructure.driving;

import fr.mycryptowallet.application.WalletService;
import fr.mycryptowallet.domain.Investment;
import fr.mycryptowallet.infrastructure.driving.dto.WalletItemDTO;
import fr.mycryptowallet.infrastructure.driving.mapper.InvestmentMapper;
import jakarta.validation.Valid;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/wallet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletResource {

    private final WalletService walletService;
    private final InvestmentMapper investmentMapper;

    public WalletResource(WalletService walletService, InvestmentMapper investmentMapper) {
        this.walletService = walletService;
        this.investmentMapper = investmentMapper;
    }

    @POST
    @Path("/value")
    public double calculateWalletValue(@Valid List<WalletItemDTO> items) {
        List<Investment> investments = investmentMapper.toInvestmentList(items);
        return walletService.totalValue(investments);
    }
}
