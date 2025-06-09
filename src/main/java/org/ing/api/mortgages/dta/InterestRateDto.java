package org.ing.api.mortgages.dta;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record InterestRateDto(int maturityPeriod, BigDecimal interestRate, OffsetDateTime lastUpdated) {
}
