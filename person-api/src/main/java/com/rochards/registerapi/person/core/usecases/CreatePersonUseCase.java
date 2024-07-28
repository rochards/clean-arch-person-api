package com.rochards.registerapi.person.core.usecases;

import com.rochards.registerapi.person.core.entities.Person;

public interface CreatePersonUseCase {

    Person execute(Person person);
}
