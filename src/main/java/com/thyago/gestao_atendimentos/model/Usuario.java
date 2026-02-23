package com.thyago.gestao_atendimentos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "cli_nome", nullable = false)
    @NotBlank
    private String nome;

    @Email
    @Column (name = "cli_email", unique = true, nullable = false)
    private String email;

    @Column (name = "cli_senha", nullable = false)
    private String senha;

    @Enumerated (EnumType.STRING)
    @Column (name = "cli_perfil")
    private Perfil perfil;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Perfil getPerfil() {
        return perfil;
    }
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }


}
