package currencyCalculator.externalapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import currencyCalculator.service.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class CalculatorHttpClient {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorHttpClient.class);
    public CalculatorHttpClient() {
        this.httpClient = HttpClient.newBuilder().build();
        this.objectMapper = new ObjectMapper();
    }

    public Exchange getExchange(String currencyFrom, String currencyTo, LocalDate day) {
        return getApiResponse(currencyFrom, currencyTo, day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .map(response -> {
                    Exchange exchange = new Exchange();
                    exchange.setCurrencyTo(currencyTo);

                    exchange.setCurrencyFrom(response.getBase());

                    exchange.setExchangeDate(LocalDate.parse(response.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                    exchange.setExchangeRate(response.getRates().get(currencyTo));
                    return exchange;

                }).orElseThrow(() -> new RuntimeException("problem pobierania danych"));
    }

    private Optional<CalculatorResponse> getApiResponse(String currencyFrom, String currencyTo, String date) {
        String requestUrl = "https://api.exchangeratesapi.io/" + date + "?base=" + currencyFrom + "&symbols=" + currencyTo;
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(requestUrl))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            CalculatorResponse asObject = objectMapper.readValue(body, CalculatorResponse.class);

            return Optional.ofNullable(asObject);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return Optional.empty();
    }


        public List<Exchange> getExchangeList(String currencyFrom, String currencyTo, LocalDate startDate, LocalDate endDate) {
        Optional<CalculatorResponsePeriod> listaMap = getApiResponseFromPeriod(currencyFrom, currencyTo, startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        List<Exchange> list = new ArrayList<>();
        listaMap.stream().map(CalculatorResponsePeriod::getRates).flatMap(resposne2 -> resposne2.entrySet().stream()).forEach(maps -> {
            String key = maps.getKey();
            List<Double> newlist = new ArrayList<>(maps.getValue().values());

                list.add(new Exchange(currencyFrom, currencyTo, newlist.get(0), LocalDate.parse(key, DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            });
return list;
        }

    private Optional<CalculatorResponsePeriod> getApiResponseFromPeriod (String currencyFrom, String currencyTo, String startDate, String endDate){
        String requestUrl = "https://api.exchangeratesapi.io/history?start_at="+startDate+"&end_at="+endDate+"&base="+currencyFrom+"&symbols="+currencyTo;
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(requestUrl))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            CalculatorResponsePeriod asObject = objectMapper.readValue(body, CalculatorResponsePeriod.class);
            return Optional.ofNullable(asObject);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return Optional.empty();
    }

}
