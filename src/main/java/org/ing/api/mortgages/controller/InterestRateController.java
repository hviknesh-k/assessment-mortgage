package org.ing.api.mortgages.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ing.api.mortgages.mapper.InterestRateMapper;
import org.ing.api.mortgages.service.InterestRateService;
import org.openapitools.model.InterestRate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class InterestRateController {

    private final InterestRateService interestRateService;

    /*
    API endpoint to fetch all existing interest rates from database
     */
    @GetMapping("/interest-rates")
    public ResponseEntity<List<InterestRate>> getAllInterestRates() {
        log.debug("Initiating process to fetch all interest rates");
        List<InterestRate> interestRates = InterestRateMapper.INTEREST_RATE_MAPPER.fromDtoToModel(interestRateService.getAllInterestRate());
        return ResponseEntity.ok().body(interestRates);
    }
}
