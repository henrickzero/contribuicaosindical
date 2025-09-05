package com.brcodigo.app.impl.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum ObservationTypeEnum {
    CONTRACT("Contrato"),
    FINANCIAL("Financeiro"),
    GERAL("Geral"),
    HEALTH("Saúde");
    private final String value;
}
