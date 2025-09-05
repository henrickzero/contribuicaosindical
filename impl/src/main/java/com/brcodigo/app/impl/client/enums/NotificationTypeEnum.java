package com.brcodigo.app.impl.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum NotificationTypeEnum {
    BLOCKING("Bloqueante"),
    NOBLOCKING("Não Bloqueante");
    private final String value;
}
