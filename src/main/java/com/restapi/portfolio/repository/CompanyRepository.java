package com.restapi.portfolio.repository;

import com.restapi.portfolio.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author "Fausto Stradiotto"
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
