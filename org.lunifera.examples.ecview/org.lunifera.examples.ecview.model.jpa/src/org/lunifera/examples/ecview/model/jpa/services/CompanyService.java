package org.lunifera.examples.ecview.model.jpa.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.examples.ecview.model.jpa.Company;
import org.lunifera.examples.ecview.model.jpa.dtos.CompanyDto;

@SuppressWarnings("all")
public class CompanyService extends AbstractDTOService<CompanyDto, Company> {
  public Class<CompanyDto> getDtoClass() {
    return CompanyDto.class;
  }
  
  public CompanyDto createDto() {
    return new CompanyDto();
  }
  
  public Class<Company> getEntityClass() {
    return Company.class;
  }
  
  public Object getId(final CompanyDto dto) {
    return dto.getId();
  }
}
