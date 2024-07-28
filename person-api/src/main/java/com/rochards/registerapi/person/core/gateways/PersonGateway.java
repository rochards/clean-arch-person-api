package com.rochards.registerapi.person.core.gateways;

import com.rochards.registerapi.person.core.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonGateway {
    Person createPerson(Person person);
    List<Person> findAllPeople();
    Optional<Person> findPersonByDocument(String document);
}
