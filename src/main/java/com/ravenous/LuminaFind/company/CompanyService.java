package com.ravenous.LuminaFind.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    boolean updateCompany(Long id, Company updatedCompany);

    void addCompany(Company company);

    boolean deleteCompanyById(Long id);
}
