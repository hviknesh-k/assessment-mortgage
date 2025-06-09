package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MortgageResponse
 */
@lombok.Builder

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-09T23:01:53.158742+02:00[Europe/Amsterdam]")
public class MortgageResponse {

  private Boolean eligible;

  private BigDecimal monthlyCosts;

  public MortgageResponse eligible(Boolean eligible) {
    this.eligible = eligible;
    return this;
  }

  /**
   * Get eligible
   * @return eligible
  */
  
  @Schema(name = "eligible", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("eligible")
  public Boolean getEligible() {
    return eligible;
  }

  public void setEligible(Boolean eligible) {
    this.eligible = eligible;
  }

  public MortgageResponse monthlyCosts(BigDecimal monthlyCosts) {
    this.monthlyCosts = monthlyCosts;
    return this;
  }

  /**
   * Get monthlyCosts
   * @return monthlyCosts
  */
  @Valid 
  @Schema(name = "monthlyCosts", example = "1500", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("monthlyCosts")
  public BigDecimal getMonthlyCosts() {
    return monthlyCosts;
  }

  public void setMonthlyCosts(BigDecimal monthlyCosts) {
    this.monthlyCosts = monthlyCosts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MortgageResponse mortgageResponse = (MortgageResponse) o;
    return Objects.equals(this.eligible, mortgageResponse.eligible) &&
        Objects.equals(this.monthlyCosts, mortgageResponse.monthlyCosts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eligible, monthlyCosts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MortgageResponse {\n");
    sb.append("    eligible: ").append(toIndentedString(eligible)).append("\n");
    sb.append("    monthlyCosts: ").append(toIndentedString(monthlyCosts)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

