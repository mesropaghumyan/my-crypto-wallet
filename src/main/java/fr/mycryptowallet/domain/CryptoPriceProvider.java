package fr.mycryptowallet.domain;

public interface CryptoPriceProvider {
    Double getPrice(String cryptoName);
}
