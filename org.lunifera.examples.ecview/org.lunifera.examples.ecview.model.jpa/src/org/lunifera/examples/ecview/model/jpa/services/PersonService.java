package org.lunifera.examples.ecview.model.jpa.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.examples.ecview.model.jpa.Person;
import org.lunifera.examples.ecview.model.jpa.dtos.PersonDto;

@SuppressWarnings("all")
public class PersonService extends AbstractDTOService<PersonDto, Person> {
  public Class<PersonDto> getDtoClass() {
    return PersonDto.class;
  }
  
  public PersonDto createDto() {
    return new PersonDto();
  }
  
  public Class<Person> getEntityClass() {
    return Person.class;
  }
  
  public Object getId(final PersonDto dto) {
    return dto.getId();
  }
}
