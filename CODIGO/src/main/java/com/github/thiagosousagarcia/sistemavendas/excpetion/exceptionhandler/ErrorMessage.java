package com.github.thiagosousagarcia.sistemavendas.excpetion.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ErrorMessage {
	
	@Getter
	private List<String> errors;
	
	public ErrorMessage(List<String> errors) {
		this.errors = errors;
	}
	
	public ErrorMessage(String error) {
		this.errors = Arrays.asList(error);
	}
	
}
