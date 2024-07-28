package com.rochards.registerapi.person.core.usecases;

import com.rochards.registerapi.person.core.entities.Person;
import com.rochards.registerapi.person.core.exceptions.BusinessException;
import com.rochards.registerapi.person.core.gateways.PersonGateway;

import java.util.Optional;

public class CreatePersonUseCaseImpl implements CreatePersonUseCase {

    private final PersonGateway personGateway;

    public CreatePersonUseCaseImpl(PersonGateway personGateway) {
        this.personGateway = personGateway;
    }

    @Override
    public Person execute(Person person) {

        Optional<Person> existentPerson = personGateway.findPersonByDocument(person.document());
        if (existentPerson.isPresent()) {
            throw new BusinessException("document '%s' already registered".formatted(person.document()));
        }

        return personGateway.createPerson(person);
    }
}
