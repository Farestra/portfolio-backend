package com.restapi.portfolio.interfaces;


import com.restapi.portfolio.models.Company;
import java.util.List;

/**
 *
 * @author "Fausto Stradiotto"
 */
public interface ICompanyService {
    public List<Company> getCompanys();
    
    public void saveCompany(Company company);
    
    public void deleteCompany(Long id);
    
    public Company findCompany(Long id);
}
