package com.rochards.registerapi.person.infrastructure.dtos;

import com.rochards.registerapi.person.core.enums.PersonType;

public record PersonRequest(
        String name,
        String document,
        PersonType type
) {
}
