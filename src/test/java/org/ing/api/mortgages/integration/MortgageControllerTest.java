package org.ing.api.mortgages.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static stubs.MortgageRequestSamples.*;
import static stubs.MortgageResponseSamples.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MortgageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("MortgageController : Fetch mortgage and verify eligibility and monthly costs")
    public void test_eligible_mortgage_scenario() throws Exception {
        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MORTGAGE_JSON_VALID_REQUEST))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(MORTGAGE_ELIGIBLE_RESPONSE, true));
    }

    @Test
    @DisplayName("MortgageController : Verify ineligibility for mortgage as salary is not sufficient")
    public void test_ineligible_mortgage_income_scenario() throws Exception {
        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MORTGAGE_JSON_NOT_ELIGIBLE_LOW_INCOME))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(MORTGAGE_INELIGIBLE_RESPONSE, true));
    }

    @Test
    @DisplayName("MortgageController : Verify ineligibility for mortgage as requested loan is higher than home value")
    public void test_ineligible_mortgage_loan_scenario() throws Exception {
        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MORTGAGE_JSON_NOT_ELIGIBLE_HIGH_LOAN))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(MORTGAGE_INELIGIBLE_RESPONSE, true));
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

    @Test
    @DisplayName("MortgageController : Verify when a non available maturity period is provided")
    public void test_invalid_input_scenario() throws Exception {
        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MORTGAGE_JSON_INVALID_MP))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(MORTGAGE_ERROR_RESPONSE, true));
    }
}
