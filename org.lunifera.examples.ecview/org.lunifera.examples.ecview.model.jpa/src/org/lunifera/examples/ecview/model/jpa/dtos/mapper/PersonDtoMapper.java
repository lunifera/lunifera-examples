package org.lunifera.examples.ecview.model.jpa.dtos.mapper;

import java.util.Date;
import org.lunifera.examples.ecview.model.jpa.Address;
import org.lunifera.examples.ecview.model.jpa.Company;
import org.lunifera.examples.ecview.model.jpa.Gender;
import org.lunifera.examples.ecview.model.jpa.Person;
import org.lunifera.examples.ecview.model.jpa.dtos.AddressDto;
import org.lunifera.examples.ecview.model.jpa.dtos.CompanyDto;
import org.lunifera.examples.ecview.model.jpa.dtos.PersonDto;
import org.lunifera.examples.ecview.model.jpa.dtos.mapper.BaseDtoMapper;

/**
 * This class maps the dto {@link PersonDto} to and from the entity {@link Person}.
 * 
 */
@SuppressWarnings("all")
public class PersonDtoMapper<DTO extends PersonDto, ENTITY extends Person> extends BaseDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link Person} to the dto {@link PersonDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final PersonDto dto, final Person entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setFirstname(toDto_firstname(entity));
    dto.setLastname(toDto_lastname(entity));
    dto.setMailaddress(toDto_mailaddress(entity));
    dto.setBirthday(toDto_birthday(entity));
    dto.setAge(toDto_age(entity));
    dto.setMarried(toDto_married(entity));
    dto.setAddress(toDto_address(entity));
    dto.setGender(toDto_gender(entity));
    dto.setImagePath(toDto_imagePath(entity));
    dto.setEmployer(toDto_employer(entity));
  }
  
  /**
   * Maps the dto {@link PersonDto} to the entity {@link Person}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final PersonDto dto, final Person entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setFirstname(toEntity_firstname(dto));
    
    entity.setLastname(toEntity_lastname(dto));
    
    entity.setMailaddress(toEntity_mailaddress(dto));
    
    entity.setBirthday(toEntity_birthday(dto));
    
    entity.setAge(toEntity_age(dto));
    
    entity.setMarried(toEntity_married(dto));
    
    entity.setAddress(toEntity_address(dto));
    
    entity.setGender(toEntity_gender(dto));
    
    entity.setImagePath(toEntity_imagePath(dto));
    
    entity.setEmployer(toEntity_employer(dto));
    
  }
  
  /**
   * Maps the property firstname from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_firstname(final Person in) {
    return in.getFirstname();
  }
  
  /**
   * Maps the property firstname from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_firstname(final PersonDto in) {
    return in.getFirstname();
  }
  
  /**
   * Maps the property lastname from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_lastname(final Person in) {
    return in.getLastname();
  }
  
  /**
   * Maps the property lastname from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_lastname(final PersonDto in) {
    return in.getLastname();
  }
  
  /**
   * Maps the property mailaddress from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_mailaddress(final Person in) {
    return in.getMailaddress();
  }
  
  /**
   * Maps the property mailaddress from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_mailaddress(final PersonDto in) {
    return in.getMailaddress();
  }
  
  /**
   * Maps the property birthday from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Date toDto_birthday(final Person in) {
    return in.getBirthday();
  }
  
  /**
   * Maps the property birthday from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Date toEntity_birthday(final PersonDto in) {
    return in.getBirthday();
  }
  
  /**
   * Maps the property age from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected int toDto_age(final Person in) {
    return in.getAge();
  }
  
  /**
   * Maps the property age from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected int toEntity_age(final PersonDto in) {
    return in.getAge();
  }
  
  /**
   * Maps the property married from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected boolean toDto_married(final Person in) {
    return in.isMarried();
  }
  
  /**
   * Maps the property married from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected boolean toEntity_married(final PersonDto in) {
    return in.isMarried();
  }
  
  /**
   * Maps the property address from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected AddressDto toDto_address(final Person in) {
    org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address> mapper = getMapper(AddressDto.class, Address.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getAddress() != null) {
    	AddressDto dto = new AddressDto();
    	mapper.mapToDTO(dto, in.getAddress());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property address from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Address toEntity_address(final PersonDto in) {
    org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address> mapper = getMapper(AddressDto.class, Address.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getAddress() != null) {
    	Address entity = new Address();
    	mapper.mapToEntity(in.getAddress(), entity);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property gender from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Gender toDto_gender(final Person in) {
    return in.getGender();
  }
  
  /**
   * Maps the property gender from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Gender toEntity_gender(final PersonDto in) {
    return in.getGender();
  }
  
  /**
   * Maps the property imagePath from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_imagePath(final Person in) {
    return in.getImagePath();
  }
  
  /**
   * Maps the property imagePath from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_imagePath(final PersonDto in) {
    return in.getImagePath();
  }
  
  /**
   * Maps the property employer from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return the mapped dto
   * 
   */
  protected CompanyDto toDto_employer(final Person in) {
    org.lunifera.dsl.dto.lib.IMapper<CompanyDto, Company> mapper = getMapper(CompanyDto.class, Company.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getEmployer() != null) {
    	CompanyDto dto = new CompanyDto();
    	mapper.mapToDTO(dto, in.getEmployer());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property employer from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return the mapped entity
   * 
   */
  protected Company toEntity_employer(final PersonDto in) {
    org.lunifera.dsl.dto.lib.IMapper<CompanyDto, Company> mapper = getMapper(CompanyDto.class, Company.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getEmployer() != null) {
    	Company entity = new Company();
    	mapper.mapToEntity(in.getEmployer(), entity);	
    	return entity;
    } else {
    	return null;
    }	
  }
}
