package org.ing.api.mortgages.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ing.api.mortgages.dta.InterestRateDto;
import org.ing.api.mortgages.repository.InterestRateRepository;
import org.ing.api.mortgages.service.InterestRateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class InterestRateServiceImpl implements InterestRateService {

    InterestRateRepository interestRateRepository;

    @Override
    public List<InterestRateDto> getAllInterestRate() {
        return interestRateRepository.findAllBy();
    }
}
