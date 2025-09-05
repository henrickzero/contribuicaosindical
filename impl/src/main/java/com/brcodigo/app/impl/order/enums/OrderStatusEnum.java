package com.brcodigo.app.impl.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum OrderStatusEnum {
    PEDING(2, "Pendente"),
    FINISHED(3, "Finalizado"),
    CANCELED(4, "Cancelado");
    private final Integer order;
    private final String value;
}
