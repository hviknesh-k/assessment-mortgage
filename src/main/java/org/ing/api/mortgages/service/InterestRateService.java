package org.ing.api.mortgages.service;

import org.ing.api.mortgages.dta.InterestRateDto;

import java.util.List;

public interface InterestRateService {
    List<InterestRateDto> getAllInterestRate();
}
