package com.thyago.gestao_atendimentos.repository;

import com.thyago.gestao_atendimentos.model.StatusAtendimento;
import com.thyago.gestao_atendimentos.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(@Email @NotBlank(message = "O campo Email não pode ficar vazio.") String email);
    Usuario findTopByStatus(StatusAtendimento status);
    boolean existsByStatusNot(StatusAtendimento status);
}


