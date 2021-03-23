package currencyCalculator.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Currency")
public class ExchangeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer Id;
    @Column
    private String currencyFrom;
    @Column
    private String currencyTo;
    @Column
    private double exchangeRate;
    @Column
    private LocalDate exchangeDate;
}
