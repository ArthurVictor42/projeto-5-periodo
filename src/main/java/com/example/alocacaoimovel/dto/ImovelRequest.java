package com.example.alocacaoimovel.dto;

public record ImovelRequest(
        String cidade,
        String bairro,
        String regiao,
        String proximidadePraia,
        String metragem,
        Integer vagasGaragens,
        Integer numerosSuite,
        Integer numerosSalas,
        Integer numerosQuartos,
        Integer numerosBanheiros,
        String possuiPiscina,
        String possuiAreaGourmet,

        String tipoImovel,
        Integer idadeImovel,
        String estadoConservacao,

        String finalidade,
        String condominio,
        Double valor
) {
}
