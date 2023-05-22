package com.contact.services.serviceImpl;

import com.contact.dto.PersonDto;
import com.contact.entities.Person;
import com.contact.exceptions.ResourceNotFoundException;
import com.contact.repositories.PersonRepo;
import com.contact.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

//DTO means Data Transfer Object
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepo personRepo;

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        personDto.setPersonId(UUID.randomUUID().toString());
        Person person = personDtoToPerson(personDto);
        Person save = personRepo.save(person);
        return personToPersonDto(save);
    }

    @Override
    public PersonDto getSinglePerson(String personId) {
        Optional<Person> personDetails = personRepo.findById(personId);
        Person person = personDetails.orElseThrow(() -> ResourceNotFoundException.builder().message("This id is not present").statusCode(HttpStatus.NOT_FOUND).build());
        return personToPersonDto(person);
    }

    @Override
    public List<PersonDto> getAllPerson() {
        List<Person> person = personRepo.findAll();
        List<PersonDto> personDtos = new ArrayList<>();
        person.stream().forEach(person1 -> personDtos.add(
                PersonDto.builder()
                        .personId(person1.getPersonId())
                        .personName(person1.getPersonName())
                        .personEmail(person1.getPersonEmail())
                        .personMobile(person1.getPersonMobile()).build()
        ));
        return personDtos;
    }

    @Override
    public String deletePerson(String personId) {
        Optional<Person> personDetails = personRepo.findById(personId);
        Person person = personDetails.orElseThrow(() -> ResourceNotFoundException.builder().message("This id is not present").statusCode(HttpStatus.NOT_FOUND).build());
        personRepo.deleteById(personId);
        return "This person is deleted";
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto, String personId) {
        Optional<Person> personDetails = personRepo.findById(personId);
        if(personDetails.isPresent()){
            Person person=personDetails.get();
            person.setPersonEmail(personDto.getPersonEmail());
            person.setPersonMobile(personDto.getPersonMobile());
            person.setPersonName(personDto.getPersonName());
            Person save = personRepo.save(person);
            return personToPersonDto(save);
        }
        else{
            throw ResourceNotFoundException.builder().message("This id is not present").statusCode(HttpStatus.NOT_FOUND).build();
        }
    }

    public Person personDtoToPerson(PersonDto personDto) {
        Person person = Person.builder()
                .personId(personDto.getPersonId())
                .personName(personDto.getPersonName())
                .personEmail(personDto.getPersonEmail())
                .personMobile(personDto.getPersonMobile())
                .build();
        return person;
    }

    public PersonDto personToPersonDto(Person person) {
        PersonDto personDto = PersonDto.builder()
                .personId(person.getPersonId())
                .personName(person.getPersonName())
                .personEmail(person.getPersonEmail())
                .personMobile(person.getPersonMobile())
                .build();
        return personDto;
    }
}
