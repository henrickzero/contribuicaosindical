package com.brcodigo.app.impl.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum GenderEnum {
    PREFER_NOT_TO_SAY("Prefiro não dizer"),
    MALE("Masculino"),
    FEMALE("Feminino"),
    NON_BINARY("Não-Binário"),
    TRANSGENDER("Transgênero"),
    GENDER_FLUID("Gênero Fluído"),
    AGENDER("Agênero"),
    OTHER("Outro");
    private final String value;
}
