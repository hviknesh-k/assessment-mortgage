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
 * MortgageRequest
 */
@lombok.Builder

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-09T23:01:53.158742+02:00[Europe/Amsterdam]")
public class MortgageRequest {

  private BigDecimal income;

  private Integer maturityPeriod;

  private BigDecimal loanValue;

  private BigDecimal homeValue;

  public MortgageRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MortgageRequest(BigDecimal income, Integer maturityPeriod, BigDecimal loanValue, BigDecimal homeValue) {
    this.income = income;
    this.maturityPeriod = maturityPeriod;
    this.loanValue = loanValue;
    this.homeValue = homeValue;
  }

  public MortgageRequest income(BigDecimal income) {
    this.income = income;
    return this;
  }

  /**
   * Get income
   * minimum: 500
   * @return income
  */
  @NotNull @Valid @DecimalMin("500") 
  @Schema(name = "income", example = "1000", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("income")
  public BigDecimal getIncome() {
    return income;
  }

  public void setIncome(BigDecimal income) {
    this.income = income;
  }

  public MortgageRequest maturityPeriod(Integer maturityPeriod) {
    this.maturityPeriod = maturityPeriod;
    return this;
  }

  /**
   * Get maturityPeriod
   * minimum: 1
   * maximum: 30
   * @return maturityPeriod
  */
  @NotNull @Min(1) @Max(30) 
  @Schema(name = "maturityPeriod", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("maturityPeriod")
  public Integer getMaturityPeriod() {
    return maturityPeriod;
  }

  public void setMaturityPeriod(Integer maturityPeriod) {
    this.maturityPeriod = maturityPeriod;
  }

  public MortgageRequest loanValue(BigDecimal loanValue) {
    this.loanValue = loanValue;
    return this;
  }

  /**
   * Get loanValue
   * minimum: 5000
   * maximum: 5000000
   * @return loanValue
  */
  @NotNull @Valid @DecimalMin("5000") @DecimalMax("5000000") 
  @Schema(name = "loanValue", example = "45000", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("loanValue")
  public BigDecimal getLoanValue() {
    return loanValue;
  }

  public void setLoanValue(BigDecimal loanValue) {
    this.loanValue = loanValue;
  }

  public MortgageRequest homeValue(BigDecimal homeValue) {
    this.homeValue = homeValue;
    return this;
  }

  /**
   * Get homeValue
   * @return homeValue
  */
  @NotNull @Valid 
  @Schema(name = "homeValue", example = "450000", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("homeValue")
  public BigDecimal getHomeValue() {
    return homeValue;
  }

  public void setHomeValue(BigDecimal homeValue) {
    this.homeValue = homeValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MortgageRequest mortgageRequest = (MortgageRequest) o;
    return Objects.equals(this.income, mortgageRequest.income) &&
        Objects.equals(this.maturityPeriod, mortgageRequest.maturityPeriod) &&
        Objects.equals(this.loanValue, mortgageRequest.loanValue) &&
        Objects.equals(this.homeValue, mortgageRequest.homeValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(income, maturityPeriod, loanValue, homeValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MortgageRequest {\n");
    sb.append("    income: ").append(toIndentedString(income)).append("\n");
    sb.append("    maturityPeriod: ").append(toIndentedString(maturityPeriod)).append("\n");
    sb.append("    loanValue: ").append(toIndentedString(loanValue)).append("\n");
    sb.append("    homeValue: ").append(toIndentedString(homeValue)).append("\n");
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

