package com.brcodigo.app.impl.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum OrderPaymentTypeEnum {
    MONEY(1, "Dinheiro"),
    CREDIT_CARD(2, "Cartão de Crédito"),
    DEBIT_CARD(3, "Cartão de Debito"),
    PIX(4, "PIX"),
    CHECK(5, "Cheque"),
    BOLETO(6, "Boleto");
    private final Integer order;
    private final String value;
}
