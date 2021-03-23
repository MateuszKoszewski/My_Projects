package currencyCalculator.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exchange {
    private String currencyFrom;
    private String currencyTo;
    private double exchangeRate;
    private LocalDate exchangeDate;

}
