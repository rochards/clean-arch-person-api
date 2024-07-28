package com.rochards.registerapi.person.infrastructure.dtos;

import com.rochards.registerapi.person.core.enums.PersonType;

public record PersonResponse(
        Long id,
        String name,
        String document,
        PersonType type
) {
}
