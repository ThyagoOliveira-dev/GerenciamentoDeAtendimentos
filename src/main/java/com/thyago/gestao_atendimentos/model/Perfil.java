package com.thyago.gestao_atendimentos.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Perfil {
    ADMIN, USUARIO;

    @JsonCreator
    public static Perfil fromString(String value) {
        for (Perfil p : Perfil.values()) {
            if (p.name().equalsIgnoreCase(value)) {
                return p;
            }
        }
        return null;
    }


}
