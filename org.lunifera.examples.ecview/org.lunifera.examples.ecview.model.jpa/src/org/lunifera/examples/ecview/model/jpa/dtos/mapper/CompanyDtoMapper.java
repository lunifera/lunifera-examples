package org.lunifera.examples.ecview.model.jpa.dtos.mapper;

import org.lunifera.examples.ecview.model.jpa.Company;
import org.lunifera.examples.ecview.model.jpa.dtos.CompanyDto;
import org.lunifera.examples.ecview.model.jpa.dtos.mapper.BaseDtoMapper;

/**
 * This class maps the dto {@link CompanyDto} to and from the entity {@link Company}.
 * 
 */
@SuppressWarnings("all")
public class CompanyDtoMapper<DTO extends CompanyDto, ENTITY extends Company> extends BaseDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link Company} to the dto {@link CompanyDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final CompanyDto dto, final Company entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setName(toDto_name(entity));
    dto.setAddress(toDto_address(entity));
  }
  
  /**
   * Maps the dto {@link CompanyDto} to the entity {@link Company}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final CompanyDto dto, final Company entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setName(toEntity_name(dto));
    
    entity.setAddress(toEntity_address(dto));
    
  }
  
  /**
   * Maps the property name from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_name(final Company in) {
    return in.getName();
  }
  
  /**
   * Maps the property name from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_name(final CompanyDto in) {
    return in.getName();
  }
  
  /**
   * Maps the property address from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_address(final Company in) {
    return in.getAddress();
  }
  
  /**
   * Maps the property address from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_address(final CompanyDto in) {
    return in.getAddress();
  }
}
