package com.thyago.gestao_atendimentos.service;

import com.thyago.gestao_atendimentos.dto.UsuarioResponseDTO;
import com.thyago.gestao_atendimentos.map.Mapper;
import com.thyago.gestao_atendimentos.model.StatusAtendimento;
import com.thyago.gestao_atendimentos.model.Usuario;
import com.thyago.gestao_atendimentos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtendimentoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Mapper mapper;

    public UsuarioResponseDTO proximo (Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            Usuario usuario1 = usuario.get();
            usuario1.setStatus(StatusAtendimento.EM_ATENDIMENTO);
            usuarioRepository.save(usuario1);
        } else{
//            Preciso fazer o tratamento para usuario não encontrado!
        }

        return mapper.toResponse(usuario.get());
    }
}
