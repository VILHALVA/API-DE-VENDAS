package com.github.thiagosousagarcia.sistemavendas.excpetion;

public class CreateVendaExcpetion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CreateVendaExcpetion (String message) {
		super(message);
	}
	
}
