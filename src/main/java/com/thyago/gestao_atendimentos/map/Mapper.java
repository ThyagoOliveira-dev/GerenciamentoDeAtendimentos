package com.thyago.gestao_atendimentos.map;

import com.thyago.gestao_atendimentos.dto.UsuarioRequestDTO;
import com.thyago.gestao_atendimentos.dto.UsuarioResponseDTO;
import com.thyago.gestao_atendimentos.model.Usuario;

@org.mapstruct.Mapper (componentModel = "spring")
public interface Mapper {
    Usuario toEntity(UsuarioRequestDTO dto);
    UsuarioResponseDTO toResponse(Usuario entidade);
}