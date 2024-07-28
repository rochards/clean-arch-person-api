package com.rochards.registerapi.person.core.usecases;

import com.rochards.registerapi.person.core.entities.Person;
import com.rochards.registerapi.person.core.gateways.PersonGateway;

import java.util.List;

public class FindAllPersonUseCaseImpl implements FindAllPersonUseCase{

    private final PersonGateway personGateway;

    public FindAllPersonUseCaseImpl(PersonGateway personGateway) {
        this.personGateway = personGateway;
    }

    @Override
    public List<Person> execute() {
        return personGateway.findAllPeople();
    }
}
