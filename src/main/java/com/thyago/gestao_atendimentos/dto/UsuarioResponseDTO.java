package com.thyago.gestao_atendimentos.dto;

import com.thyago.gestao_atendimentos.model.Perfil;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String perfil,
        String status
        ) {


}
