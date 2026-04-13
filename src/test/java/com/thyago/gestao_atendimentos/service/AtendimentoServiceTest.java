package com.thyago.gestao_atendimentos.service;

import com.thyago.gestao_atendimentos.dto.UsuarioResponseDTO;
import com.thyago.gestao_atendimentos.exception.customizedException.EmailJaCadastradoException;
import com.thyago.gestao_atendimentos.exception.customizedException.UsuarioNaoEncontradoException;
import com.thyago.gestao_atendimentos.map.Mapper;
import com.thyago.gestao_atendimentos.model.Usuario;
import com.thyago.gestao_atendimentos.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AtendimentoServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private AtendimentoService atendimentoService;

    @Test
    @DisplayName("Should pass, if the user not exist in server")
    void buscaPorID() {
        //Arrange
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(1L, "", "", "","" );
        Usuario usuarioCriado = new Usuario();

        usuarioCriado.setId(1L);

        when(usuarioRepository.findById(usuarioCriado.getId())).thenReturn(Optional.empty());

        //Act
        assertThrows(UsuarioNaoEncontradoException.class, () -> atendimentoService.proximoPorID(1L));
    }
}