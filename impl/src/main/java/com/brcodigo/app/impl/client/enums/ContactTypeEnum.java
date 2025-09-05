package com.brcodigo.app.impl.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum ContactTypeEnum {
    MOBILE("Celular"),
    HOME("Casa"),
    COMPANY("Empresa");
    private final String value;
}
