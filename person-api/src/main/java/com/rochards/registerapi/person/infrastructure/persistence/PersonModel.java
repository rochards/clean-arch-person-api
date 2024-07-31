package com.rochards.registerapi.person.infrastructure.persistence;

import com.rochards.registerapi.person.core.enums.PersonType;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "TB_PERSON")
public class PersonModel {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String document;

    @Enumerated(EnumType.STRING)
    private PersonType type;

    /**
     * @deprecated for JPA usage
     */
    @Deprecated
    public PersonModel() {
    }

    public PersonModel(String name, String document, PersonType type) {
        this.name = name;
        this.document = document;
        this.type = type;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonModel that = (PersonModel) o;
        return Objects.equals(name, that.name) && Objects.equals(document, that.document) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, document, type);
    }
}
