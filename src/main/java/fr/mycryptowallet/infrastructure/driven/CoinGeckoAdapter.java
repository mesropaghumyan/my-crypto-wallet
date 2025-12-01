package fr.mycryptowallet.infrastructure.driven;

import fr.mycryptowallet.domain.CryptoPriceProvider;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Map;

@ApplicationScoped
public class CoinGeckoAdapter implements CryptoPriceProvider {

    private final CoinGeckoClient coinGeckoClient;

    public CoinGeckoAdapter(@RestClient CoinGeckoClient coinGeckoClient) {
        this.coinGeckoClient = coinGeckoClient;
    }

    @Override
    public Double getPrice(String cryptoName) {
        String cryptoId = cryptoName.toLowerCase();

        try {
            Map<String, Map<String, Double>> response = coinGeckoClient.getPrice(cryptoId, "eur");

            if (response.containsKey(cryptoId) && response.get(cryptoId).containsKey("eur")) {
                return response.get(cryptoId).get("eur");
            }
        }
        catch (Exception e) {
            System.err.println("Erreur lors de l'appel CoinGecko: " + e.getMessage());
        }

        return 0.0;
    }
}
