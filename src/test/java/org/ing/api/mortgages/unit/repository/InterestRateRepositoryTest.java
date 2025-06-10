package org.ing.api.mortgages.unit.repository;


import org.ing.api.mortgages.dta.InterestRateDto;
import org.ing.api.mortgages.entity.InterestRateEntity;
import org.ing.api.mortgages.repository.InterestRateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class InterestRateRepositoryTest {

    @Autowired
    private InterestRateRepository interestRateRepository;

    @ParameterizedTest
    @MethodSource("provideMaturityPeriod")
    @DisplayName("InterestRateRepository : Verify fetch for a given maturity period")
    void test_fetch_one_interest_rates_scenario(int maturityPeriod, BigDecimal expectedInterestRate) {
        Optional<InterestRateDto> interestRate = interestRateRepository.findByMaturityPeriod(maturityPeriod);
        assertTrue(interestRate.isPresent());
        assertEquals(0, expectedInterestRate.compareTo(interestRate.get().interestRate()));
    }

    private static Stream<Arguments> provideMaturityPeriod() {
        return Stream.of(
                Arguments.of(10, BigDecimal.valueOf(6.70)),
                Arguments.of(15, BigDecimal.valueOf(5.80)),
                Arguments.of(20, BigDecimal.valueOf(4.00)),
                Arguments.of(30, BigDecimal.valueOf(2.50))
        );
    }

    @Test
    @DisplayName("InterestRateRepository : Verify whether saved interest rates are fetched")
    void test_fetch_all_interest_rates_scenarios() {
        List<InterestRateEntity> interestRates = interestRateRepository.findAll();
        assertEquals(4, interestRates.size());
    }

}
