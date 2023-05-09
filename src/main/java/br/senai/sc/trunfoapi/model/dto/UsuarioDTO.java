package br.senai.sc.trunfoapi.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {
    @NotNull
    private String nome;
    @NotNull
    private String senha;
}
