package fr.mycryptowallet.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Investment {
    private final String cryptoName;
    private final double quantity;

    /**
     * @param marketPrice - Le prix du marché
     * @return Le montant de l'investissement en fonction du prix du marché
     */
    public double value(double marketPrice) {
        return getQuantity() * marketPrice;
    }
}
