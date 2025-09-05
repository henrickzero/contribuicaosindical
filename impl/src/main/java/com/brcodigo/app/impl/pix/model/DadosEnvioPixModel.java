package com.brcodigo.app.impl.pix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DadosEnvioPixModel {
    private String nomeDestinatario;
    private String chaveDestinatario;
    private BigDecimal valor;
    private String valorStr;
    private String cidadeRemetente;
    private String descricao;
    private String idTransacao;
}