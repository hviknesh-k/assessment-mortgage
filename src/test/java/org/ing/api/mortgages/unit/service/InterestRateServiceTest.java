package org.ing.api.mortgages.unit.service;

import org.ing.api.mortgages.dta.InterestRateDto;
import org.ing.api.mortgages.repository.InterestRateRepository;
import org.ing.api.mortgages.service.impl.InterestRateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InterestRateServiceTest {

    @InjectMocks
    private InterestRateServiceImpl interestRateService;

    @Mock
    private InterestRateRepository interestRateRepository;

    List<InterestRateDto> listOfEntities;

    @BeforeEach
    void setup(){

        listOfEntities = List.of(
                new InterestRateDto(10, new BigDecimal(6.70), getDate("2025-04-10T11:00:00")),
                new InterestRateDto(15, new BigDecimal(5.80), getDate("2025-06-10T11:00:00")),
                new InterestRateDto(20, new BigDecimal(4.00), getDate("2025-05-11T11:00:00")),
                new InterestRateDto(30, new BigDecimal(2.50), getDate("2025-05-12T11:00:00")));
    }

    @Test
    @DisplayName("InterestRateService : Verify whether saved interest rates are fetched")
    void test_get_all_interest_rates() {
        given(interestRateRepository.findAllBy()).willReturn(listOfEntities);
        List<InterestRateDto> interestRates = interestRateService.getAllInterestRate();
        assertThat(interestRates).isNotNull();
        assertEquals(4, interestRates.size());
    }

    @Test
    @DisplayName("InterestRateService : Verify fetch for a given maturity period")
    void test_fetch_single_interest_rates() {
        for (InterestRateDto entity : listOfEntities) {
            given(interestRateRepository.findByMaturityPeriod(entity.maturityPeriod()))
                    .willReturn(Optional.of(entity));
            InterestRateDto interestRateDto = interestRateService.getRateForMaturity(entity.maturityPeriod());
            assertEquals(0, entity.interestRate().compareTo(interestRateDto.interestRate()));
        }

    }

    private LocalDateTime getDate(String date) {
        return LocalDateTime.parse(date);
    }
}
