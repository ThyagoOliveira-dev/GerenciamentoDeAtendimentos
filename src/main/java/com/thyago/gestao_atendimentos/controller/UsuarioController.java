package com.thyago.gestao_atendimentos.controller;


import com.thyago.gestao_atendimentos.model.Usuario;
import com.thyago.gestao_atendimentos.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public Usuario criarUsuario(@Valid @RequestBody Usuario novoUsuario){
        return usuarioRepository.save(novoUsuario);
    }
}

