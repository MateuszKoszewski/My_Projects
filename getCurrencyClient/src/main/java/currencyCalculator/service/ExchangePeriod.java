package currencyCalculator.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangePeriod {
    private String currencyFrom;
    private String currencyTo;
    private Map<LocalDate, Double> exchangeRate;

}
