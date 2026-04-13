package com.thyago.gestao_atendimentos.controller;

import com.thyago.gestao_atendimentos.dto.UsuarioRequestDTO;
import com.thyago.gestao_atendimentos.dto.UsuarioResponseDTO;
import com.thyago.gestao_atendimentos.map.Mapper;
import com.thyago.gestao_atendimentos.model.Usuario;
import com.thyago.gestao_atendimentos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private Mapper mapper;

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioResponseDTO> salvar(@RequestBody @Valid UsuarioRequestDTO dto) {

        UsuarioResponseDTO resultado = usuarioService.criar(dto);

        return ResponseEntity.status(201).body(resultado);
    }

    @GetMapping("/usuarios")
    public List<Usuario> informarTodosUsuarios(){
        return usuarioService.informarTodosUsuarios();
    }

}