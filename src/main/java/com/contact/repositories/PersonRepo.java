package com.contact.repositories;

import com.contact.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person,String> {
}
