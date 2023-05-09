package br.senai.sc.trunfoapi.model.entity;

import br.senai.sc.trunfoapi.model.enums.Posicao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Carta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Posicao posicao;
    private int estrelaFinta, estrelaPernaRuim;
    private int velocidade, chute, passe, conducao, defesa, fisico;
    private boolean trunfo;
}
