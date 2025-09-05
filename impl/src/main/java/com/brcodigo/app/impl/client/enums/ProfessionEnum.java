package com.brcodigo.app.impl.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum ProfessionEnum {
    PREFER_NOT_TO_SAY("Outros","Prefiro não dizer"),
    MEDICO("Saúde", "Médico(a)"),
    ENFERMEIRO("Saúde", "Enfermeiro(a)"),
    DENTISTA("Saúde", "Dentista"),
    FISIOTERAPEUTA("Saúde", "Fisioterapeuta"),
    FARMACEUTICO("Saúde", "Farmacêutico(a)"),
    PSICOLOGO("Saúde", "Psicólogo(a)"),

    PROFESSOR("Educação", "Professor(a)"),
    PEDAGOGO("Educação", "Pedagogo(a)"),
    COORDENADOR_PEDAGOGICO("Educação", "Coordenador(a) Pedagógico"),
    DIRETOR_ESCOLA("Educação", "Diretor(a) de Escola"),

    DESENVOLVEDOR_SOFTWARE("Tecnologia da Informação", "Desenvolvedor(a) de Software"),
    ANALISTA_SISTEMAS("Tecnologia da Informação", "Analista de Sistemas"),
    ENGENHEIRO_SOFTWARE("Tecnologia da Informação", "Engenheiro(a) de Software"),
    TECNICO_INFORMATICA("Tecnologia da Informação", "Técnico(a) em Informática"),
    ANALISTA_SEGURANCA_INFORMACAO("Tecnologia da Informação", "Analista de Segurança da Informação"),

    ENGENHEIRO_CIVIL("Engenharia", "Engenheiro(a) Civil"),
    ENGENHEIRO_MECANICO("Engenharia", "Engenheiro(a) Mecânico"),
    ENGENHEIRO_ELETRICISTA("Engenharia", "Engenheiro(a) Eletricista"),
    ENGENHEIRO_PRODUCAO("Engenharia", "Engenheiro(a) de Produção"),
    ENGENHEIRO_QUIMICO("Engenharia", "Engenheiro(a) Químico"),

    ADVOGADO("Direito", "Advogado(a)"),
    JUIZ("Direito", "Juiz(a)"),
    PROMOTOR_JUSTICA("Direito", "Promotor(a) de Justiça"),
    DELEGADO_POLICIA("Direito", "Delegado(a) de Polícia"),

    ADMINISTRADOR_EMPRESAS("Administração e Negócios", "Administrador(a) de Empresas"),
    ECONOMISTA("Administração e Negócios", "Economista"),
    CONTADOR("Administração e Negócios", "Contador(a)"),
    GERENTE_PROJETOS("Administração e Negócios", "Gerente de Projetos"),

    DESIGNER_GRAFICO("Artes e Design", "Designer Gráfico"),
    ARQUITETO("Artes e Design", "Arquiteto(a)"),
    ARTISTA_PLASTICO("Artes e Design", "Artista Plástico(a)"),
    FOTOGRAFO("Artes e Design", "Fotógrafo(a)"),

    JORNALISTA("Comunicação e Marketing", "Jornalista"),
    PUBLICITARIO("Comunicação e Marketing", "Publicitário(a)"),
    RELACOES_PUBLICAS("Comunicação e Marketing", "Relações Públicas"),
    MARKETING_DIGITAL("Comunicação e Marketing", "Marketing Digital"),

    CHEF_COZINHA("Serviços", "Chef de Cozinha"),
    CABELEIREIRO("Serviços", "Cabeleireiro(a)"),
    ESTETICISTA("Serviços", "Esteticista"),
    MECANICO_AUTOMOVEIS("Serviços", "Mecânico(a) de Automóveis"),

    POLICIAL("Setor Público", "Policial"),
    BOMBEIRO("Setor Público", "Bombeiro(a)"),
    FUNCIONARIO_PUBLICO("Setor Público", "Funcionário(a) Público(a)"),
    MILITAR("Setor Público", "Militar"),

    AGRICULTOR("Outras Profissões", "Agricultor(a)"),
    PESCADOR("Outras Profissões", "Pescador(a)"),
    ARTESAO("Outras Profissões", "Artesão(ã)"),
    COMERCIANTE("Outras Profissões", "Comerciante");

    private final String categoria;
    private final String descricao;
}
