package com.brcodigo.app.impl.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum ClientTypeEnum {
    CLIENT("Cliente"),
    COMPANY("Empresa"),
    SUPPLIER("Fornecedor"),
    EMPLOYEE("Empregado"),
    PERSONAL("Personal"),
    TEACHER("Professor"),
    OUTSOURCED("Tercerizado");
    private final String value;
}
