package com.rochards.registerapi.person.core.entities;

import com.rochards.registerapi.person.core.enums.PersonType;

public record Person(
        Long id,
        String name,
        String document,
        PersonType type
) {
}
