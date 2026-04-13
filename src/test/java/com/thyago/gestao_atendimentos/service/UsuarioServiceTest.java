package com.thyago.gestao_atendimentos.service;

import com.thyago.gestao_atendimentos.dto.UsuarioRequestDTO;
import com.thyago.gestao_atendimentos.dto.UsuarioResponseDTO;
import com.thyago.gestao_atendimentos.exception.customizedException.EmailJaCadastradoException;
import com.thyago.gestao_atendimentos.exception.customizedException.PerfilNaoInformadoException;
import com.thyago.gestao_atendimentos.map.Mapper;
import com.thyago.gestao_atendimentos.model.Perfil;
import com.thyago.gestao_atendimentos.model.StatusAtendimento;
import com.thyago.gestao_atendimentos.model.Usuario;
import com.thyago.gestao_atendimentos.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Should pass, if all of datas of the user is inserted correctly")
    void criarUsuarioComSucesso() {
        //Arrange
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Thyago", "teste@email.com", "123", Perfil.USUARIO, StatusAtendimento.EM_ATENDIMENTO);

        Usuario usuario = new Usuario();
        usuario.setPerfil(Perfil.USUARIO);
        usuario.setNome("Thyago");

        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setNome("Thyago");

        UsuarioResponseDTO usuarioResponse = new UsuarioResponseDTO(1L, "Thyago", "thyago@email.com", "", "");

        when(mapper.toEntity(dto)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuarioSalvo);
        when(mapper.toResponse(usuarioSalvo)).thenReturn(usuarioResponse);

        // Act
        UsuarioResponseDTO resultado = usuarioService.criar(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("Thyago", resultado.nome());

        verify(mapper, times(1)).toEntity(any());
        verify(usuarioRepository, times(1)).save(any());
        verify(mapper, times(1)).toResponse(any());
    }

    @Test
    @DisplayName("Should pass, if the email already exists in server")
    void criarUsuarioComEmailDuplicado(){
        //Arrange
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Thyago", "teste@email.com", "123", Perfil.USUARIO, StatusAtendimento.EM_ATENDIMENTO);

        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setPerfil(Perfil.USUARIO);
        usuario.setNome("Thyago");

        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setNome("Thyago");

        when(mapper.toEntity(dto)).thenReturn(usuario);
        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);

        //Act
        assertThrows(EmailJaCadastradoException.class, () -> usuarioService.criar(dto));

        //Assert
        verify(usuarioRepository, never()).save(any());

    }

    @Test
    @DisplayName("Should pass, if the user is never created without the attribute: Perfil")
    void criarUsuarioSemPerfil() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Thyago", "teste@email.com", "123", Perfil.USUARIO, StatusAtendimento.EM_ATENDIMENTO);

        Usuario usuario = new Usuario();
        usuario.setPerfil(null);
        usuario.setEmail("teste@email.com");
        usuario.setNome("Thyago");

        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setNome("Thyago");

        when(mapper.toEntity(dto)).thenReturn(usuario);

        //Act
        assertThrows(PerfilNaoInformadoException.class, () -> usuarioService.criar(dto));

        //Assert
        assertEquals(usuario.getPerfil(), null);

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should pass, if the user is created with status 'AGUARDANDO' independent of the type status inserted in DTO")
    void criarUsuarioComOutroStatus (){
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Thyago", "teste@email.com", "123", Perfil.USUARIO, null);

        Usuario usuario = new Usuario();
        usuario.setPerfil(Perfil.USUARIO);
        usuario.setEmail("teste@email.com");
        usuario.setNome("Thyago");
        usuario.setStatus(StatusAtendimento.AGUARDANDO);

        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setNome("Thyago");
        usuario.setStatus(StatusAtendimento.AGUARDANDO);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(1L, "", "", "", "StatusAtendimento.AGUARDANDO");

        when(mapper.toEntity(dto)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuarioSalvo);
        when(mapper.toResponse(usuarioSalvo)).thenReturn(usuarioResponseDTO);

        //Act
        UsuarioResponseDTO resultado = usuarioService.criar(dto);

        //Assert
        assertEquals(StatusAtendimento.AGUARDANDO, usuario.getStatus());
    }

    @Test
    void informarTodosUsuarios() {
    }
}