import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

    public class ConvertirMoneda {

        public double convertirMoneda(double cantidad, String opcion) throws IOException, InterruptedException {
            String json = obtenerTasasDeCambio();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            JsonObject conversionRatesObject = jsonObject.getAsJsonObject("conversion_rates");

            ApiResponse response = new ApiResponse(conversionRatesObject);

            double tasaConversion = 0.0;
            String monedaOrigen = "";
            String monedaDestino = "";

            switch (opcion) {
                case "1":
                    monedaOrigen = "USD";
                    monedaDestino = "ARS";
                    break;
                case "2":
                    monedaOrigen = "ARS";
                    monedaDestino = "USD";
                    break;
                case "3":
                    monedaOrigen = "USD";
                    monedaDestino = "BRL";
                    break;
                case "4":
                    monedaOrigen = "BRL";
                    monedaDestino = "USD";
                    break;
                case "5":
                    monedaOrigen = "USD";
                    monedaDestino = "COP";
                    break;
                case "6":
                    monedaOrigen = "COP";
                    monedaDestino = "USD";
                    break;
                case "7":
                    monedaOrigen = "PEN";
                    monedaDestino = "USD";
                    break;
                case "8":
                    monedaOrigen = "USD";
                    monedaDestino = "PEN";
                    break;
                case "9":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Seleccione una opcion entre 1 al 9.");
                    break;
            }

            tasaConversion = response.getConversionRate(monedaOrigen, monedaDestino);
            System.out.println("Resultado de la conversión: " + monedaOrigen + " => " +  monedaDestino);
            return cantidad * tasaConversion;

        }

        private String obtenerTasasDeCambio() throws IOException, InterruptedException {
            URI uri = URI.create("https://v6.exchangerate-api.com/v6/2a4a06e1b389618c7791317f/latest/USD");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }

        static class ApiResponse {
            private JsonObject conversionRates;
            public ApiResponse(JsonObject conversionRates) {
                this.conversionRates = conversionRates;
            }

            public double getConversionRate(String from, String to) {
                // Obtener la tasa de conversión de la moneda de origen a la moneda de destino
                double tasaOrigen = conversionRates.get(from).getAsDouble();
                double tasaDestino = conversionRates.get(to).getAsDouble();
                return tasaDestino / tasaOrigen;
            }
        }
    }

