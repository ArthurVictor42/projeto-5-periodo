package com.example.alocacaoimovel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "caracteristica_imovel")
public class CaracteristicaImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caracteristica")
    private Long idCaracteristica;

    private Double metragem;
    private Integer vagasGaragens;
    private Integer numerosSalas;
    private Integer numerosQuartos;
    private Integer numerosBanheiros;
    private String possuiPiscina;
    private String possuiAreaGourmet;
    private String tipoImovel;
    private Integer idadeImovel;
    private String estadoConservacao;
}
