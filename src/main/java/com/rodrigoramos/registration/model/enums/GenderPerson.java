package com.rodrigoramos.registration.model.enums;

import lombok.Getter;

@Getter
public enum GenderPerson {

    MALE(1, "Male"),
    FEMALE(2, "Female"),
    PREFERE_NOT_TO_SAY(3, "Prefer not to say");

    private final int cod;
    private final String description;

    GenderPerson(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public static GenderPerson toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (GenderPerson x : GenderPerson.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
