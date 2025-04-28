package br.com.jvsanmartin.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException() {
        super("Username already exists!");
    }
}
