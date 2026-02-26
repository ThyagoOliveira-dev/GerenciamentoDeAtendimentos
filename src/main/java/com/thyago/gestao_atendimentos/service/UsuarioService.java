package com.thyago.gestao_atendimentos.service;

import com.thyago.gestao_atendimentos.dto.UsuarioRequestDTO;
import com.thyago.gestao_atendimentos.dto.UsuarioResponseDTO;
import com.thyago.gestao_atendimentos.map.Mapper;
import com.thyago.gestao_atendimentos.model.Usuario;
import com.thyago.gestao_atendimentos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Mapper mapper;

    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuario){

        Usuario entidade = mapper.toEntity(usuario);
        Usuario usuarioSalvo = usuarioRepository.save(entidade);

        return mapper.toResponse(usuarioSalvo);
    }
}
