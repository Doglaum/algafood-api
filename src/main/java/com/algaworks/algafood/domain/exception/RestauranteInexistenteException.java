package com.algaworks.algafood.domain.exception;

public class RestauranteInexistenteException extends RuntimeException {
    public RestauranteInexistenteException(String message) {
        super(message);
    }
}
