package fr.mycryptowallet.infrastructure.driven;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

@RegisterRestClient(configKey = "coingecko-api")
public interface CoinGeckoClient {

    @GET
    @Path("/simple/price")
    Map<String, Map<String, Double>> getPrice(
            @QueryParam("ids") String cryptoId,
            @QueryParam("vs_currencies") String currency
    );
}
