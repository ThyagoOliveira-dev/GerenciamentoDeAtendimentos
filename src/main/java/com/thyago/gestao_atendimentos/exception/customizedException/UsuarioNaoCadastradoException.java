package com.thyago.gestao_atendimentos.exception.customizedException;

public class UsuarioNaoCadastradoException extends RuntimeException {
    public UsuarioNaoCadastradoException (String mensagem){
        super(mensagem);
    }
}
