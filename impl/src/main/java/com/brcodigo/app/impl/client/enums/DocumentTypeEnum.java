package com.brcodigo.app.impl.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum DocumentTypeEnum {
    CPF("CPF"),
    CNPJ("CNPJ"),
    DOC_FOREIGN_IDENTITY("Documento de Identificação do Estrangeiro");
    private final String value;
}
