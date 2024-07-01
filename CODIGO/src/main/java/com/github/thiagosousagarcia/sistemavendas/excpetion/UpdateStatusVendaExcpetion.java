package com.github.thiagosousagarcia.sistemavendas.excpetion;

public class UpdateStatusVendaExcpetion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UpdateStatusVendaExcpetion (String message) {
		super(message);
	}
	
}
