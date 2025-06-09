package org.ing.api.mortgages.repository;

import org.ing.api.mortgages.dta.InterestRateDto;
import org.ing.api.mortgages.entity.InterestRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRateRepository extends JpaRepository<InterestRateEntity, Long> {
    List<InterestRateDto> findAllBy();
}
