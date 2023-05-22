package com.contact.services;

import com.contact.dto.PersonDto;

import java.util.List;

public interface PersonService {
    public PersonDto createPerson(PersonDto personDto);
    public PersonDto getSinglePerson(String personId);
    public List<PersonDto>getAllPerson();
    public String deletePerson(String personId);
    public PersonDto updatePerson(PersonDto personDto,String personId);
}
