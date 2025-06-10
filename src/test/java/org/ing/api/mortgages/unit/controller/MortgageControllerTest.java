package org.ing.api.mortgages.unit.controller;

import lombok.extern.slf4j.Slf4j;
import org.ing.api.mortgages.controller.MortgageController;
import org.ing.api.mortgages.service.MortgageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static stubs.MortgageRequestSamples.*;
import static stubs.MortgageResponseSamples.MORTGAGE_ELIGIBLE_RESPONSE;
import static stubs.MortgageResponseSamples.MORTGAGE_INVALID_INPUT_ERROR_RESPONSE;

@Slf4j
@WebMvcTest(MortgageController.class)
public class MortgageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MortgageService mortgageService;

    @Test
    @DisplayName("MortgageController : Fetch mortgage and verify eligibility and monthly costs")
    public void test_eligible_mortgage_scenario() throws Exception {
        given(mortgageService.getMonthlyCosts(MORTGAGE_REQUEST_VALID_MP_10)).willReturn(new BigDecimal(3437.05));
        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MORTGAGE_JSON_VALID_REQUEST))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(MORTGAGE_ELIGIBLE_RESPONSE, true));
    }

    @Test
    @DisplayName("MortgageController : Verify when invalid loan amount is supplied")
    public void test_invalid_input_amount_scenario() throws Exception {
        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MORTGAGE_JSON_NOT_ELIGIBLE_INVALID_LOAN_AMOUNT))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(MORTGAGE_INVALID_INPUT_ERROR_RESPONSE, true));
    }

}
