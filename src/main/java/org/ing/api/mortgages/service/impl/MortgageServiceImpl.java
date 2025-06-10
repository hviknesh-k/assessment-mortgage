package org.ing.api.mortgages.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ing.api.mortgages.dta.InterestRateDto;
import org.ing.api.mortgages.exception.ErrorCode;
import org.ing.api.mortgages.exception.MortgageServiceException;
import org.ing.api.mortgages.exception.MortgageValidationException;
import org.ing.api.mortgages.service.InterestRateService;
import org.ing.api.mortgages.service.MortgageService;
import org.openapitools.model.MortgageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class MortgageServiceImpl implements MortgageService {

    private static final int MONTHS_IN_YEAR = 12;
    private static final int ROUND_OFF_12 = 12;
    private static final int SALARY_THRESHOLD =4;
    private final InterestRateService interestRateService;


    /**
     * Method to calculate eligibility and monthly costs for mortgage
     *
     * @param request - Incoming API request
     * @return Calculated monthly costs
     */
    @Override
    public BigDecimal getMonthlyCosts(final MortgageRequest request) {
        performValidation(request);
        InterestRateDto interestRateEntity = interestRateService.getRateForMaturity(request.getMaturityPeriod());
        if (null == interestRateEntity.interestRate()) {
            log.error("No interest rates configured for maturity {}", request.getMaturityPeriod());
            throw new MortgageServiceException(ErrorCode.SERVER_ERROR);
        }
        BigDecimal interestRatePerMonth = interestRateEntity.interestRate().divide(BigDecimal.valueOf(100), ROUND_OFF_12, RoundingMode.HALF_UP);
        BigDecimal numerator = ratePerMonth(interestRatePerMonth).multiply(calculateRatePower(interestRatePerMonth, request.getMaturityPeriod()));
        BigDecimal denominator = calculateRatePower(interestRatePerMonth, request.getMaturityPeriod()).subtract(BigDecimal.ONE);
        BigDecimal ratio = numerator.divide(denominator, ROUND_OFF_12, RoundingMode.HALF_UP);
        return request.getLoanValue().multiply(ratio).setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal ratePerMonth(final BigDecimal rate) {
        return rate.divide(BigDecimal.valueOf(MONTHS_IN_YEAR), ROUND_OFF_12, RoundingMode.HALF_UP);
    }

    private static BigDecimal calculateRatePower(final BigDecimal rate, final int maturityPeriod) {
        BigDecimal onePlus = BigDecimal.ONE.add(ratePerMonth(rate));
        return onePlus.pow(maturityPeriod * MONTHS_IN_YEAR);
    }

    /**
     * Method to perform additional business validations on request
     * @param request - Incoming api request
     */
    private void performValidation(MortgageRequest request) {
        if (request.getLoanValue().compareTo(request.getHomeValue()) > 0) {
            throw new MortgageValidationException(ErrorCode.HIGH_MORTGAGE_REQUEST);
        }

        if (request.getLoanValue()
                .compareTo(request.getIncome().multiply(BigDecimal.valueOf(SALARY_THRESHOLD))) > 0) {
            throw new MortgageValidationException(ErrorCode.INSUFFICIENT_INCOME);
        }

    }

}
