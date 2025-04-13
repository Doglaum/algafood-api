package com.algaworks.algafood.domain.exception;

public class CozinhaJaExisteException extends RuntimeException {
    public CozinhaJaExisteException(String message) {
        super(message);
    }
}
