package com.contact.controllers;

import com.contact.dto.PersonDto;
import com.contact.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/person")
public class PersonController {
    Logger logger= LoggerFactory.getLogger(PersonController.class);
    @Autowired
    PersonService service;
    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto){
        PersonDto person = service.createPerson(personDto);
        ResponseEntity<PersonDto>response=new ResponseEntity<>(person, HttpStatus.OK);
        return response;
    }
    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> getSinglePerson(@PathVariable(value = "personId")String personId){
        PersonDto singlePerson = service.getSinglePerson(personId);
        return new ResponseEntity<>(singlePerson,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllPerson(){
        List<PersonDto> allPerson = service.getAllPerson();
        return new ResponseEntity<>(allPerson,HttpStatus.OK);
    }
    @DeleteMapping("/{personId}")
    public ResponseEntity<Map<String, Object>>deleteSinglePerson(@PathVariable(value = "personId") String personId){
        String message = service.deletePerson(personId);
        Map<String,Object>map=new HashMap<>();
        map.put("message",message);
        map.put("statusCode",HttpStatus.NO_CONTENT);
        ResponseEntity<Map<String,Object>>response=new ResponseEntity<>(map,HttpStatus.OK);
        return response;
    }
    @PutMapping("/{personId}")
    public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonDto personDto,@PathVariable(value = "personId") String personId){
        PersonDto personDto1 = service.updatePerson(personDto, personId);
        ResponseEntity<PersonDto> responseEntity=new ResponseEntity<>(personDto1,HttpStatus.CREATED);
        return responseEntity;
    }
}
