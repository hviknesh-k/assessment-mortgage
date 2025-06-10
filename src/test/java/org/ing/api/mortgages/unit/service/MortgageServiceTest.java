package org.ing.api.mortgages.unit.service;

import lombok.extern.slf4j.Slf4j;
import org.ing.api.mortgages.dta.InterestRateDto;
import org.ing.api.mortgages.exception.ErrorCode;
import org.ing.api.mortgages.exception.MortgageServiceException;
import org.ing.api.mortgages.exception.MortgageValidationException;
import org.ing.api.mortgages.service.InterestRateService;
import org.ing.api.mortgages.service.impl.MortgageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.MortgageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.ing.api.mortgages.exception.ErrorCode.SERVER_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static stubs.MortgageRequestSamples.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class MortgageServiceTest {

    @InjectMocks
    private MortgageServiceImpl mortgageService;
    @Mock
    private InterestRateService interestRateService;

    List<InterestRateDto> listOfEntities;

    public static Stream<Arguments> provideMaturityRequest() {
        return Stream.of(
                Arguments.of(MORTGAGE_REQUEST_VALID_MP_10, true, BigDecimal.valueOf(3437.05)),
                Arguments.of(MORTGAGE_REQUEST_VALID_MP_15, true, BigDecimal.valueOf(2499.27)),
                Arguments.of(MORTGAGE_REQUEST_VALID_MP_30, true, BigDecimal.valueOf(1185.36)));
    }

    @BeforeEach
    void setup(){
        listOfEntities = List.of(
                new InterestRateDto(10, new BigDecimal(6.70), getDate("2025-04-10T11:00:00")),
                new InterestRateDto(15, new BigDecimal(5.80), getDate("2025-06-10T11:00:00")),
                new InterestRateDto(20, new BigDecimal(4.00), getDate("2025-05-11T11:00:00")),
                new InterestRateDto(30, new BigDecimal(2.50), getDate("2025-05-12T11:00:00")));
    }

    private InterestRateDto getEntity(int maturityPeriod) {
        return listOfEntities.stream()
                .filter(x -> x.maturityPeriod() == maturityPeriod)
                .findAny().orElse(null);
    }


    @Test
    @DisplayName("MortgageService : Verify when invalid maturity period is provided")
    void test_mortgage_details_exception_scenario() {
        given(interestRateService.getRateForMaturity(MORTGAGE_REQUEST_VALID_MP_11.getMaturityPeriod()))
                .willReturn(new InterestRateDto(11, null, null));
        MortgageServiceException exception = assertThrows(MortgageServiceException.class, () -> mortgageService.getMonthlyCosts(MORTGAGE_REQUEST_VALID_MP_11));
        assertEquals(SERVER_ERROR.getCode(), exception.getErrorCode().getCode());
    }

    @ParameterizedTest
    @MethodSource("provideMaturityRequest")
    @DisplayName("MortgageService : Verify monthyl costs calcualtion for provided maturity periods")
    void test_fetch_eligible_mortgage_details_scenario(MortgageRequest request, boolean eligibility, BigDecimal expectedMonthlyCosts) {

        InterestRateDto entity = getEntity(request.getMaturityPeriod());
        given(interestRateService.getRateForMaturity(entity.maturityPeriod()))
                    .willReturn(entity);
        BigDecimal monthlyCosts = mortgageService.getMonthlyCosts(request);
        assertEquals(0, expectedMonthlyCosts.compareTo(monthlyCosts));
    }

    @ParameterizedTest
    @MethodSource("provideValidationCases")
    @DisplayName("MortgageService : Verify business validation and invalid inputs")
    void test_mortgage_details_exception_scenario(String testCase, MortgageRequest request, String expectedErrorCode) {
        log.info("Case {} ", testCase);
        MortgageValidationException exception = assertThrows(MortgageValidationException.class, () -> mortgageService.getMonthlyCosts(request));
        assertEquals(expectedErrorCode, exception.getErrorCode().getCode());
    }

    private static Stream<Arguments> provideValidationCases() {
        return Stream.of(
                Arguments.of("Income is not sufficient" ,MORTGAGE_REQUEST_NOT_ELIGIBLE_LOW_INCOME, ErrorCode.INSUFFICIENT_INCOME.getCode()),
                Arguments.of("Request amount higher than home value", MORTGAGE_REQUEST_NOT_ELIGIBLE_HIGH_LOAN, ErrorCode.HIGH_MORTGAGE_REQUEST.getCode()));
    }

    private LocalDateTime getDate(String date) {
        return LocalDateTime.parse(date);
    }

}
