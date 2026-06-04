package com.ucsal.estoque.exception;

public class ItemNaoEncontrado extends RuntimeException {
    public ItemNaoEncontrado(String message) {
        super(message);
    }
}
