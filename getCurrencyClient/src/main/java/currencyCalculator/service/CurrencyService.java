package currencyCalculator.service;

import currencyCalculator.database.DatabaseDao;
import currencyCalculator.database.DatabaseDaoHibernateImpl;
import currencyCalculator.externalapi.CalculatorHttpClient;
import net.bytebuddy.asm.Advice;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class CurrencyService {

    private final CalculatorHttpClient calculatorHttpClient;
    private final DatabaseDao databaseDao;

    public CurrencyService() {
        this.calculatorHttpClient = new CalculatorHttpClient();
        this.databaseDao = new DatabaseDaoHibernateImpl();
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Berlin"));
    }

    public CurrencyService(CalculatorHttpClient calculatorHttpClient, DatabaseDao databaseDao) {
        this.calculatorHttpClient = calculatorHttpClient;
        this.databaseDao = databaseDao;
    }

    public BigDecimal exchange(String currencyFrom, String currencyTo, LocalDate day) {
        return exchange(currencyFrom, currencyTo, day, BigDecimal.ONE);
    }

    public BigDecimal exchange(String currencyFrom, String currencyTo, LocalDate day, BigDecimal amount) {
        Exchange exchangeInfo = getFromDatabaseOrExternalApi(currencyFrom, currencyTo, day);
        return amount.multiply(BigDecimal.valueOf(exchangeInfo.getExchangeRate()));
    }

    private Exchange getFromDatabaseOrExternalApi(String currencyFrom, String currencyTo, LocalDate day) {
        Exchange data = databaseDao.getByCriteria(currencyFrom, currencyTo, day);
        if (data == null) {
            data = calculatorHttpClient.getExchange(currencyFrom, currencyTo, day);
            databaseDao.save(data);
        }

        return data;
    }

    public List<String> exchangeFromPeriod(String currencyFrom, String currencyTo, LocalDate startDate, LocalDate endDate) {

        List<Exchange> newList = getFromDatabaseOrExternalApiPeriod( currencyFrom, currencyTo, startDate, endDate);
        List<String> listOfString = new ArrayList<>();
        for (int i=0; i<newList.size();i++){
            String newRecord = "From " + currencyFrom + " to " + currencyTo + " date : " + newList.get(i).getExchangeDate() + "rate is " + newList.get(i).getExchangeRate();
            listOfString.add(newRecord);
        }
        return listOfString;
    }

    private List<Exchange> getFromDatabaseOrExternalApiPeriod(String currencyFrom, String currencyTo, LocalDate startDate, LocalDate endDate) {
        int period = Period.between(startDate, endDate).getDays();
        List<Exchange> newList = new ArrayList<>();
        LocalDate dateStart = startDate;
        for (int i = 0; i < period; i++) {
            LocalDate newDate = dateStart.plus(Period.of(0, 0, i));

            Exchange data = databaseDao.getByCriteria(currencyFrom, currencyTo, newDate);
            if (data == null){
                data=calculatorHttpClient.getExchange(currencyFrom,currencyTo,newDate);
                databaseDao.save(data);
            }
            newList.add(data);
        }
       return newList;
    }

}
