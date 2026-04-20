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

    @Column(name = "metragem")
    private String metragem;

    @Column(name = "vagas_garagens")
    private int vagasGaragens;

    @Column(name = "numeros_suite")
    private int numerosSuite;

    @Column(name = "numeros_salas")
    private int numerosSalas;

    @Column(name = "numeros_quartos")
    private int numerosQuartos;

    @Column(name = "numeros_banheiros")
    private int numerosBanheiros;

    @Column(name = "possui_piscina")
    private String possuiPiscina;

    @Column(name = "possui_area_gourmet")
    private String possuiAreaGourmet;

    @Column(name = "tipo_imovel")
    private String tipoImovel;

    @Column(name = "idade_imovel")
    private int idadeImovel;

    @Column(name = "estado_conservacao")
    private String estadoConservacao;

}
