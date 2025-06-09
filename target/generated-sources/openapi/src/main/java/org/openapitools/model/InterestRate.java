package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * InterestRate
 */
@lombok.Builder

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-09T23:01:53.158742+02:00[Europe/Amsterdam]")
public class InterestRate {

  private Integer maturityPeriod;

  private BigDecimal interestRate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastUpdated;

  public InterestRate maturityPeriod(Integer maturityPeriod) {
    this.maturityPeriod = maturityPeriod;
    return this;
  }

  /**
   * Get maturityPeriod
   * @return maturityPeriod
  */
  
  @Schema(name = "maturityPeriod", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maturityPeriod")
  public Integer getMaturityPeriod() {
    return maturityPeriod;
  }

  public void setMaturityPeriod(Integer maturityPeriod) {
    this.maturityPeriod = maturityPeriod;
  }

  public InterestRate interestRate(BigDecimal interestRate) {
    this.interestRate = interestRate;
    return this;
  }

  /**
   * Get interestRate
   * @return interestRate
  */
  @Valid 
  @Schema(name = "interestRate", example = "7.8", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("interestRate")
  public BigDecimal getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(BigDecimal interestRate) {
    this.interestRate = interestRate;
  }

  public InterestRate lastUpdated(OffsetDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
    return this;
  }

  /**
   * Get lastUpdated
   * @return lastUpdated
  */
  @Valid 
  @Schema(name = "lastUpdated", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastUpdated")
  public OffsetDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(OffsetDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InterestRate interestRate = (InterestRate) o;
    return Objects.equals(this.maturityPeriod, interestRate.maturityPeriod) &&
        Objects.equals(this.interestRate, interestRate.interestRate) &&
        Objects.equals(this.lastUpdated, interestRate.lastUpdated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maturityPeriod, interestRate, lastUpdated);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InterestRate {\n");
    sb.append("    maturityPeriod: ").append(toIndentedString(maturityPeriod)).append("\n");
    sb.append("    interestRate: ").append(toIndentedString(interestRate)).append("\n");
    sb.append("    lastUpdated: ").append(toIndentedString(lastUpdated)).append("\n");
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

