package org.ing.api.mortgages.unit.controller;


import org.ing.api.mortgages.controller.InterestRateController;
import org.ing.api.mortgages.dta.InterestRateDto;
import org.ing.api.mortgages.service.InterestRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static stubs.InterestRateResponseSamples.INTEREST_RATE_VALID_RESPONSE;

@WebMvcTest(InterestRateController.class)
class InterestRateControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterestRateService interestRateService;

    List<InterestRateDto> listOfEntities;

    @BeforeEach
    void setup(){

        listOfEntities = List.of(
                new InterestRateDto(10, new BigDecimal(6.70), getDate("2025-04-10T00:00:00")),
                new InterestRateDto(15, new BigDecimal(5.80), getDate("2025-06-10T00:00:00")),
                new InterestRateDto(20, new BigDecimal(4.00), getDate("2025-05-11T00:00:00")),
                new InterestRateDto(30, new BigDecimal(2.50), getDate("2025-05-12T00:00:00")));
    }

    @Test
    @DisplayName("InterestRateController : Validate GET endpoint to fetch all available interest rates")
    void test_fetch_all_interest_rates() throws Exception {
        given(interestRateService.getAllInterestRate()).willReturn(listOfEntities);
        mockMvc.perform(get("/api/interest-rates")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(INTEREST_RATE_VALID_RESPONSE, true));

    }

    private LocalDateTime getDate(String date) {
        return LocalDateTime.parse(date);
    }

}
