package com.thyago.gestao_atendimentos.service;

import com.thyago.gestao_atendimentos.model.Usuario;
import com.thyago.gestao_atendimentos.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(@Valid Usuario usuario) {

        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new IllegalArgumentException("O usuário já tem esse email cadastrado");
        } else {
            usuarioRepository.save(usuario);
        }

        return usuario;
    }
}
