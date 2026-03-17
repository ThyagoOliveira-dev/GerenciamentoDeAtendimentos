package com.thyago.gestao_atendimentos.service;

import com.thyago.gestao_atendimentos.dto.UsuarioResponseDTO;
import com.thyago.gestao_atendimentos.exception.customizedException.UsuarioNaoEncontradoException;
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

    public UsuarioResponseDTO proximoLista() {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findTopByStatus(StatusAtendimento.AGUARDANDO));

        if (usuario.isPresent()) {
            Usuario usuario1 = usuario.get();
            usuario1.setStatus(StatusAtendimento.EM_ATENDIMENTO);
            usuarioRepository.save(usuario1);
            if (!usuarioRepository.existsByStatusNot(StatusAtendimento.EM_ATENDIMENTO)) {
                throw new UsuarioNaoEncontradoException("Não existem clientes aguardando atendimento!");
            }
        }

        return mapper.toResponse(usuario.get());
    }

    public UsuarioResponseDTO proximoPorID(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            Usuario usuario1 = usuario.get();
            usuario1.setStatus(StatusAtendimento.EM_ATENDIMENTO);
            usuarioRepository.save(usuario1);
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado! Por favor verifique o ID informado.");
        }

        return mapper.toResponse(usuario.get());
    }

    public UsuarioResponseDTO finalizar(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            Usuario usuario1 = usuario.get();
            if (usuario1.getStatus() == StatusAtendimento.EM_ATENDIMENTO) {
                usuario1.setStatus(StatusAtendimento.FINALIZADO);
                usuarioRepository.save(usuario1);
            }
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado! Verifique se o status do cliente é: " + "Em antedimento" + "E se digitou corretamente o ID do cliente.");
        }
        return mapper.toResponse(usuario.get());
    }

    public String deletar(Long id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            Usuario usuario1 = usuario.get();
            usuarioRepository.delete(usuario1);
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado! Verifique se o ID do cliente foi escrito corretamente!");
        }

        Usuario usuario1 = usuario.get();

        return usuario1.getNome() + " com o ID: " + usuario1.getId();
    }
}
