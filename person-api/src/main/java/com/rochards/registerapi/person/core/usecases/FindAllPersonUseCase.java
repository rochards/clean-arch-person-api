package com.rochards.registerapi.person.core.usecases;

import com.rochards.registerapi.person.core.entities.Person;

import java.util.List;

public interface FindAllPersonUseCase {
    List<Person> execute();
}
