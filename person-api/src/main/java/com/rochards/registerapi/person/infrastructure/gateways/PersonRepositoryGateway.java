package com.rochards.registerapi.person.infrastructure.gateways;

import com.rochards.registerapi.person.core.entities.Person;
import com.rochards.registerapi.person.core.gateways.PersonGateway;
import com.rochards.registerapi.person.infrastructure.persistence.PersonEntity;
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

        PersonEntity personEntity = new PersonEntity(person.name(), person.document(), person.type());

        personRepository.save(personEntity);

        return new Person(personEntity.getId(), personEntity.getName(), personEntity.getDocument(), personEntity.getType());
    }

    @Override
    public List<Person> findAllPeople() {

        List<PersonEntity> peopleEntity = personRepository.findAll();

        return peopleEntity.stream()
                .map(personEntity -> new Person(personEntity.getId(), personEntity.getName(), personEntity.getDocument(), personEntity.getType()))
                .toList();
    }

    @Override
    public Optional<Person> findPersonByDocument(String document) {

        Optional<PersonEntity> optPersonEntity = personRepository.findByDocument(document);

        Person person = optPersonEntity
                .map(personEntity -> new Person(personEntity.getId(), personEntity.getName(), personEntity.getDocument(), personEntity.getType()))
                .orElse(null);

        return Optional.ofNullable(person);
    }
}
