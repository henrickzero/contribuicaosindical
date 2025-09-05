package com.brcodigo.app.impl.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum ParentTypeEnum {
    CONJUGE("Cônjuge"),
    PAI("Pai"),
    FILHO("Filho"),
    IRMAO("Irmão"),
    AVO_MASCULINO("Avô"),
    NETO("Neto"),
    BISAVO_MASCULINO("Bisavô"),
    BISNETO("Bisneto"),
    TIO("Tio"),
    SOBRINHO("Sobrinho"),
    PRIMO("Primo"),
    SOGRO("Sogro"),
    GENRO("Marido da filha"),
    CUNHADO("Cunhado"),
    PADRASTO("Marido da mãe"),
    ENTEADO("Filho do cônjuge");

    private final String value;
}
