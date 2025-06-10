package org.ing.api.mortgages.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interest_rates")
public class
InterestRateEntity {
    @Id
    @Column(name = "maturity_period")
    private int maturityPeriod;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastUpdated;

}
