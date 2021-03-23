package currencyCalculator.externalapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;
@Data
public class CalculatorResponse {
    private Map<String, Double> rates;
    private String base;
    private String date;

}

/*
{
  "rates": {
    "CAD": 1.5619,
    "MYR": 4.9199
  },
  "base": "EUR",
  "date": "2020-12-02"
}
 */
