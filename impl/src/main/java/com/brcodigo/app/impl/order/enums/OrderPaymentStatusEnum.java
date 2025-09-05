package com.brcodigo.app.impl.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum OrderPaymentStatusEnum {
    PEDING(1, "Pendente"),
    FINISHED(2, "Finalizado"),
    CANCELED(3, "Cancelado");
    private final Integer order;
    private final String value;
}
