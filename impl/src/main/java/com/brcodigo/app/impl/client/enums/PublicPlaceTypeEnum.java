package com.brcodigo.app.impl.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum PublicPlaceTypeEnum {
    RUA("Rua"),
    AVENIDA("Avenida"),
    ESTRADA("Estrada"),
    TRAVESSA("Travessa"),
    AEROPORTO("Aeroporto"),
    ALAMEDA("Alameda"),
    AREA("Área"),
    CAMPO("Campo"),
    CHACARA("Chácara"),
    COLONIA("Colônia"),
    CONDOMINIO("Condomínio"),
    CONJUNTO("Conjunto"),
    DISTRITO("Distrito"),
    ESPLANADA("Esplanada"),
    ESTACAO("Estação"),
    FAVELA("Favela"),
    FAZENDA("Fazenda"),
    FEIRA("Feira"),
    JARDIM("Jardim"),
    LADEIRA("Ladeira"),
    LAGO("Lago"),
    LAGOA("Lagoa"),
    LARGO("Largo"),
    LOTEAMENTO("Loteamento"),
    MORRO("Morro"),
    NUCLEO("Núcleo"),
    PARQUE("Parque"),
    PASSARELA("Passarela"),
    PATIO("Pátio"),
    PRACA("Praça"),
    QUADRA("Quadra"),
    RECANTO("Recanto"),
    RESIDENCIAL("Residencial"),
    RODOVIA("Rodovia"),
    SETOR("Setor"),
    SITIO("Sítio"),
    TRECHO("Trecho"),
    TREVO("Trevo"),
    VALE("Vale"),
    VEREDA("Vereda"),
    VIA("Via"),
    VIADUTO("Viaduto"),
    VIELA("Viela"),
    VILA("Vila");
    private final String descricao;
}
