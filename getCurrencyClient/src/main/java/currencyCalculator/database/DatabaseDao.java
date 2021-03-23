package currencyCalculator.database;

import currencyCalculator.service.Exchange;

import java.time.LocalDate;
import java.util.Optional;

public interface DatabaseDao {

    Exchange getByCriteria(String currencyFrom, String currencyTo, LocalDate day);

    void save(Exchange data);
}
