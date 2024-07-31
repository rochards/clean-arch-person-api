package com.rochards.registerapi.person.infrastructure.gateways;

import com.rochards.registerapi.person.core.entities.Person;
import com.rochards.registerapi.person.core.gateways.PersonGateway;
import com.rochards.registerapi.person.infrastructure.persistence.PersonModel;
import com.rochards.registerapi.person.infrastructure.persistence.PersonRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonRepositoryGateway implements PersonGateway {

    private final PersonRepository personRepository;

    public PersonRepositoryGateway(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person createPerson(Person person) {

        PersonModel personModel = new PersonModel(person.name(), person.document(), person.type());

        personRepository.save(personModel);

        return new Person(personModel.getId(), personModel.getName(), personModel.getDocument(), personModel.getType());
    }

    @Override
    public List<Person> findAllPeople() {

        List<PersonModel> peopleEntity = personRepository.findAll();

        return peopleEntity.stream()
                .map(personModel -> new Person(personModel.getId(), personModel.getName(), personModel.getDocument(), personModel.getType()))
                .toList();
    }

    @Override
    public Optional<Person> findPersonByDocument(String document) {

        Optional<PersonModel> optPersonEntity = personRepository.findByDocument(document);

        Person person = optPersonEntity
                .map(personModel -> new Person(personModel.getId(), personModel.getName(), personModel.getDocument(), personModel.getType()))
                .orElse(null);

        return Optional.ofNullable(person);
    }
}
