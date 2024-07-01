package com.github.thiagosousagarcia.sistemavendas.excpetion;

public class VendaNotFoundExcpetion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public VendaNotFoundExcpetion (String message) {
		super(message);
	}
	
}
