package com.brcodigo.app.impl.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum MaritalStatusEnum {
    PREFER_NOT_TO_SAY("Prefiro não dizer"),
    SINGLE("Solteiro(a)"),
    MARRIED("Casado(a)"),
    DIVORCED("Divorciado(a)"),
    WIDOWED("Viúvo(a)"),
    LEGALLY_SEPARATED("Separado(a) Judicialmente"),
    DOMESTIC_PARTNERSHIP("União Estável"),
    CIVIL_PARTNERSHIP("Parceria Civil");
    private final String value;
}
