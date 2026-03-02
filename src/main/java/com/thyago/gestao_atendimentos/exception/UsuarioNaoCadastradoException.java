package com.thyago.gestao_atendimentos.exception;

public class UsuarioNaoCadastradoException extends RuntimeException {
    public UsuarioNaoCadastradoException (String mensagem){
        super(mensagem);
    }
}
