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
@Table(name = "negociacao")
public class Negociacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_negociacao")
    private Long idNegociacao;

    @Column(name = "finalidade")
    private String finalidade;

    @Column(name = "condominio")
    private String condominio;

    @Column(name = "valor")
    private Double valor;
}
