package com.example.alocacaoimovel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Localizacao {

    private Long idLocalizacao;
    private String cidade;
    private String bairro;
    private String regiao;
    private String proximidade;
}
