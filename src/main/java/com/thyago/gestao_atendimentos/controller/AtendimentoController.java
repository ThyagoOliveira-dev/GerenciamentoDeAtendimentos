package com.thyago.gestao_atendimentos.controller;

import com.thyago.gestao_atendimentos.dto.UsuarioResponseDTO;
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

        UsuarioResponseDTO oqueMudou = atendimentoService.proximoPorID(id);

        return ResponseEntity.status(201).body(oqueMudou);
    }

    @PatchMapping("/proximo")
    public ResponseEntity<UsuarioResponseDTO> proximoLista() {

        UsuarioResponseDTO proximoCliente = atendimentoService.proximoLista();

        return ResponseEntity.status(201).body(proximoCliente);
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<UsuarioResponseDTO> finalizar(@PathVariable Long id) {

        UsuarioResponseDTO finalizar = atendimentoService.finalizar(id);

        return ResponseEntity.status(201).body(finalizar);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletar(@PathVariable Long id) {

        String usuarioDeletado = atendimentoService.deletar(id);

        //return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body("OK o usuário: " + usuarioDeletado + " foi deletado com sucesso!");

    }
}
