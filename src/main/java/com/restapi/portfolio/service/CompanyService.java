
package com.restapi.portfolio.service;

import com.restapi.portfolio.interfaces.ICompanyService;
import com.restapi.portfolio.models.Company;
import com.restapi.portfolio.repository.CompanyRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author "Fausto Stradiotto"
 */
@Service 
@Transactional
public class CompanyService implements ICompanyService{
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getCompanys() {
        return companyRepository.findAll();
    }

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company findCompany(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        return company;
    }
    
}
