package com.thyago.gestao_atendimentos.exception;

import com.thyago.gestao_atendimentos.exception.customizedException.EmailJaCadastradoException;
import com.thyago.gestao_atendimentos.exception.customizedException.PerfilNaoInformadoException;
import com.thyago.gestao_atendimentos.exception.customizedException.UsuarioNaoCadastradoException;
import com.thyago.gestao_atendimentos.exception.customizedException.UsuarioNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsuarioNaoCadastradoException.class)
    public ResponseEntity<Object> handleCustomException(RuntimeException ex) {
        ObjetoErro dto = new ObjetoErro();
        dto.setError(ex.getMessage());
        dto.setStatus(HttpStatus.NOT_FOUND.value());
        dto.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ObjetoErro dto = new ObjetoErro();
        dto.setError("Erro de validação nos campos");
        dto.setCode(erros);
        dto.setStatus(status.value());
        dto.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(dto, headers, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneral(Exception ex) {
        ObjetoErro dto = new ObjetoErro();
        dto.setError("Ocorreu um erro interno inesperado.");
        dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dto.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ObjetoErro> handleEmailDuplicado(EmailJaCadastradoException ex) {
        ObjetoErro dto = new ObjetoErro();
        dto.setError(ex.getMessage());
        dto.setStatus(HttpStatus.CONFLICT.value());
        dto.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PerfilNaoInformadoException.class)
    public ResponseEntity<ObjetoErro> handlePerfil(PerfilNaoInformadoException ex) {
        ObjetoErro dto = new ObjetoErro();
        dto.setError(ex.getMessage());
        dto.setStatus(HttpStatus.CONFLICT.value());
        dto.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ObjetoErro> handleUsuario(UsuarioNaoEncontradoException ex){
        ObjetoErro dto = new ObjetoErro();
        dto.setError(ex.getMessage());
        dto.setStatus(HttpStatus.CONFLICT.value());
        dto.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }
}
