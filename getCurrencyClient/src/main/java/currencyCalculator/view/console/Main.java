package currencyCalculator.view.console;

import currencyCalculator.externalapi.CalculatorHttpClient;
import currencyCalculator.service.CurrencyService;
import currencyCalculator.service.Exchange;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        CurrencyService currencyService = new CurrencyService();

//currencyService.exchange("PLN", "EUR", LocalDate.now());

//        currencyService.exchangeFromPeriod("PLN","EUR",LocalDate.of(2020,1,1),LocalDate.of(2020,1,21));
        currencyService.exchange("PLN","EUR",LocalDate.of(2020,1,1));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        LocalDate dateStart = LocalDate.of(2020, 1, 1);
//        for (int i = 0; i < 6; i++) {
//            LocalDate newDate = dateStart.plus(Period.of(0, 0, i));
//            System.out.println(newDate);
//        }
    }
}
