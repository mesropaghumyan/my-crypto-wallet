package fr.mycryptowallet.application;

import fr.mycryptowallet.domain.CryptoPriceProvider;
import fr.mycryptowallet.domain.Investment;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class WalletService {
    private final CryptoPriceProvider priceProvider;

    public WalletService(CryptoPriceProvider priceProvider) {
        this.priceProvider = priceProvider;
    }

    /**
     * @param investments - Liste des investissements
     * @return Le prix total selon le marché de l'ensemble des investissement
     */
    public double totalValue(List<Investment> investments) {
        double total = 0.0;

        for (Investment investment : investments) {
            Double price = priceProvider.getPrice(investment.getCryptoName());

            if (price != null) {
                total += investment.value(price);
            }
        }

        return total;
    }
}
