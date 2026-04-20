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
@Table(name = "imovel")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imovel")
    private Long idImovel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_localizacao")
    private Localizacao localizacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_caracteristica")
    private CaracteristicaImovel  caracteristicaImovel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_negociacao")
    private Negociacao negociacao;


}
