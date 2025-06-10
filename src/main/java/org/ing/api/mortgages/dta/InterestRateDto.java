package org.ing.api.mortgages.dta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InterestRateDto(int maturityPeriod, BigDecimal interestRate, LocalDateTime lastUpdated) {
}
