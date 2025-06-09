package org.ing.api.mortgages;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    /*
   API endpoint to fetch all existing interest rates from database
    */
    @GetMapping("/interest-rates")
    public ResponseEntity<List<InterestRate>> getAllInterestRates() {
        return ResponseEntity.ok().body(null);
    }

}
