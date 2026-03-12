package com.thyago.gestao_atendimentos.service;

import com.thyago.gestao_atendimentos.dto.UsuarioRequestDTO;
import com.thyago.gestao_atendimentos.dto.UsuarioResponseDTO;
import com.thyago.gestao_atendimentos.exception.EmailJaCadastradoException;
import com.thyago.gestao_atendimentos.exception.PerfilNaoInformadoException;
import com.thyago.gestao_atendimentos.map.Mapper;
import com.thyago.gestao_atendimentos.model.Perfil;
import com.thyago.gestao_atendimentos.model.StatusAtendimento;
import com.thyago.gestao_atendimentos.model.Usuario;
import com.thyago.gestao_atendimentos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private Mapper mapper;


    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuario) {
        Usuario entidade = mapper.toEntity(usuario);
        if (usuarioRepository.existsByEmail(entidade.getEmail())) {
            throw new EmailJaCadastradoException("Email já cadastrado no nosso sistema!");
        }
        if (entidade.getPerfil() == null) {
            throw new PerfilNaoInformadoException("Perfil inválido! Escolha ADMIN ou USUARIO.");
        }
        entidade.setStatus(StatusAtendimento.AGUARDANDO);
        Usuario usuarioSalvo = usuarioRepository.save(entidade);
        return mapper.toResponse(usuarioSalvo);

    }

    public List<Usuario> informarTodosUsuarios() {
        return usuarioRepository.findAll();
    }
}
