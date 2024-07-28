package com.rochards.registerapi.person.infrastructure.controllers;


import com.rochards.registerapi.person.core.entities.Person;
import com.rochards.registerapi.person.core.usecases.CreatePersonUseCase;
import com.rochards.registerapi.person.infrastructure.dtos.PersonRequest;
import com.rochards.registerapi.person.infrastructure.dtos.PersonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/people")
public class CreatePersonController {

    private final CreatePersonUseCase createPersonUseCase;

    public CreatePersonController(CreatePersonUseCase createPersonUseCase) {
        this.createPersonUseCase = createPersonUseCase;
    }

    @PostMapping
    public ResponseEntity<PersonResponse> create(@RequestBody PersonRequest request) {

        Person newPerson = new Person(null, request.name(), request.document(), request.type());
        newPerson = createPersonUseCase.execute(newPerson);

        PersonResponse response = new PersonResponse(newPerson.id(), newPerson.name(), newPerson.document(), newPerson.type());

        return ResponseEntity
                .created(
                        URI.create("/api/v1/people/%s".formatted(response.id()))
                )
                .body(response);
    }
}
