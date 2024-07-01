package com.github.thiagosousagarcia.sistemavendas.excpetion.exceptionhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.thiagosousagarcia.sistemavendas.excpetion.ClienteNotFoundExcpetion;
import com.github.thiagosousagarcia.sistemavendas.excpetion.CreateVendaExcpetion;
import com.github.thiagosousagarcia.sistemavendas.excpetion.ProdutoNotFoundException;
import com.github.thiagosousagarcia.sistemavendas.excpetion.UpdateStatusVendaExcpetion;
import com.github.thiagosousagarcia.sistemavendas.excpetion.VendaNotFoundExcpetion;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(CreateVendaExcpetion.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handle(CreateVendaExcpetion ex) {
		return new ErrorMessage(ex.getMessage());
	}
	
	@ExceptionHandler(UpdateStatusVendaExcpetion.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handle(UpdateStatusVendaExcpetion ex) {
		return new ErrorMessage(ex.getMessage());
	}
	
	@ExceptionHandler(VendaNotFoundExcpetion.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handle(VendaNotFoundExcpetion ex) {
		return new ErrorMessage(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handle(MethodArgumentNotValidException ex) {
		List<String> erros = new ArrayList<>();
		
		erros = ex.getBindingResult().getAllErrors()
							.stream().map(erro -> erro.getDefaultMessage())
							.collect(Collectors.toList());
		
		return new ErrorMessage(erros);
	}
	
	@ExceptionHandler(ClienteNotFoundExcpetion.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handle(ClienteNotFoundExcpetion ex) {
		return new ErrorMessage(ex.getMessage());
	}
	
	@ExceptionHandler(ProdutoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handle(ProdutoNotFoundException ex) {
		return new ErrorMessage(ex.getMessage());
	}
		
}
