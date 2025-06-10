package org.ing.api.mortgages.service;


import org.openapitools.model.MortgageRequest;

import java.math.BigDecimal;

public interface MortgageService {

    BigDecimal getMonthlyCosts(MortgageRequest request);
}
