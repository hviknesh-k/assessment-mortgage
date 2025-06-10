package org.ing.api.mortgages.integration;


import org.ing.api.mortgages.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static stubs.InterestRateResponseSamples.INTEREST_RATE_VALID_RESPONSE;

@AutoConfigureMockMvc
public class InterestRateIntegrationTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("InterestRateController : Validate GET endpoint to fetch all available interest rates")
    public void test_fetch_all_interest_rates() throws Exception {
        mockMvc.perform(get("/api/interest-rates")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(INTEREST_RATE_VALID_RESPONSE, true));
    }
}
