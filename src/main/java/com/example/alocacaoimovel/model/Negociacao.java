package com.example.alocacaoimovel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Negociacao {

    private Long idNegociacao;
    private String finalidade;
    private String condominio;
    private Double valor;
}
