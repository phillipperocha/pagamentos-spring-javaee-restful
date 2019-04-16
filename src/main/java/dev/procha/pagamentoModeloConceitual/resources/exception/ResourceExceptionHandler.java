package dev.procha.pagamentoModeloConceitual.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.procha.pagamentoModeloConceitual.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	// Agora faremos um método para utilizar a excessão.
	// Temos que utilizar a anotação @ExceptionHandler() e passar o tipo de exceção que ela trata
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e
			, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),
				e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	// Esse formato acima é padrão do @ControllerAdvice.
	// Estamos implementando uma ação básica do framework que obrigatoriamente tem que ter
	// a assinatura do método acima.
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, 
			HttpServletRequest request) {
		// Aqui geraremos o código Http Bad_Request, sempre se mantendo nas boas práticas
		// temos que enviar o código que condiza com o erro.
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),
				e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
