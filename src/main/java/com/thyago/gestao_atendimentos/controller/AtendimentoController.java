package com.thyago.gestao_atendimentos.controller;

import com.thyago.gestao_atendimentos.dto.UsuarioRequestDTO;
import com.thyago.gestao_atendimentos.dto.UsuarioResponseDTO;
import com.thyago.gestao_atendimentos.model.Usuario;
import com.thyago.gestao_atendimentos.repository.UsuarioRepository;
import com.thyago.gestao_atendimentos.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @PatchMapping("/{id}/proximo")
    public ResponseEntity<UsuarioResponseDTO> proximo(@PathVariable Long id) {

        UsuarioResponseDTO oqueMudou = atendimentoService.proximo(id);

        return ResponseEntity.status(201).body(oqueMudou);
    }

//    @PatchMapping("/{id}/finalizar")
}
