package org.ing.api.mortgages.mapper;

import org.ing.api.mortgages.dta.InterestRateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.InterestRate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InterestRateMapper {

    InterestRateMapper INTEREST_RATE_MAPPER = Mappers.getMapper(InterestRateMapper.class);
    List<InterestRate> fromDtoToModel(final List<InterestRateDto> interestRate);

}
