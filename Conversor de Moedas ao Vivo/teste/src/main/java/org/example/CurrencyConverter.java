import java.net.http.*;
import java.net.URI;
import java.io.IOException;
import com.google.gson.Gson;

public class CurrencyConverter {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/feba47fd4dd516777cf2fdf3/latest/USD";

    public static ExchangeRateResponse getExchangeRates() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                return gson.fromJson(response.body(), ExchangeRateResponse.class);
            } else {
                System.out.println("Erro na API: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao conectar à API: " + e.getMessage());
            return null;
        }
    }
}
