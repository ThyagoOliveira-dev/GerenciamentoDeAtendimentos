package com.thyago.gestao_atendimentos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(

        @NotBlank(message = "O nome deve ser informado")
        @Size(min = 2, message = "O nome deve ter no mínimo 2 caracteres")
        String nome,

        @Email
        @NotBlank (message = "O campo Email não pode ficar vazio.")
        String email,

        @NotBlank (message = "A senha não pode ser vazia.")
        String senha
) {

}
