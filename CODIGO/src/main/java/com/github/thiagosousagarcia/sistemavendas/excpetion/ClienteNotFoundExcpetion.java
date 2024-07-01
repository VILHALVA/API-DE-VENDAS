package com.github.thiagosousagarcia.sistemavendas.excpetion;

public class ClienteNotFoundExcpetion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ClienteNotFoundExcpetion (String message) {
		super(message);
	}
}
