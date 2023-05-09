package br.senai.sc.trunfoapi.model.dto;

import br.senai.sc.trunfoapi.model.enums.Posicao;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartaDTO {
    private String nome;
    private Posicao posicao;
//    @Min(1)
//    @Max(5)
    private int estrelaFinta, estrelaPernaRuim;
//    @Min(1)
//    @Max(99)
    private int velocidade, chute, passe, conducao, defesa, fisico;
    private boolean trunfo;
}
