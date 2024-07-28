package com.rochards.registerapi.person.infrastructure.controllers;


import com.rochards.registerapi.person.core.entities.Person;
import com.rochards.registerapi.person.core.usecases.FindAllPersonUseCase;
import com.rochards.registerapi.person.infrastructure.dtos.PersonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class FindPersonController {

    private final FindAllPersonUseCase findAllPersonUseCase;

    public FindPersonController(FindAllPersonUseCase findAllPersonUseCase) {
        this.findAllPersonUseCase = findAllPersonUseCase;
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> findAll() {
        List<Person> people = findAllPersonUseCase.execute();

        List<PersonResponse> response = people.stream()
                .map(person -> new PersonResponse(person.id(), person.name(), person.document(), person.type()))
                .toList();

        return ResponseEntity.ok(response);
    }
}
