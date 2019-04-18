package dev.procha.pagamentoModeloConceitual.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.procha.pagamentoModeloConceitual.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e
			, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),
				e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, 
			HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),
				e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	// Vamos chamar o método de Validation, fica a vontade
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, 
			HttpServletRequest request) {
		
		// StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), 
		//				e.getMessage(), System.currentTimeMillis());
		
		// Até agora está tudo certo, retornará o código do bad request, a mensagem, o time stamp
		// Mas agora eu quero mostrar uma coisa nova, quero mostrar a lista de erros
		// Queremos mostrar o campo referente ao erro e depois a String contendo o erro
		// nome : "Preenchimento obritarório" por exemplo
		
		// Criaremos uma classe auxiliar para carregar esses dados, o nome do campo e a mensagem de erro dele
		// Chamaremos de FieldMessage
		// E aí criaremos um erro auxliar no lugar do StandardError
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), 
				"Erro de validação", System.currentTimeMillis());
		
		// E agora antes de retornar esse dado temos que percorrer a lista de erros que estouram na exceção
		// Pegando apenas o nome do campo e a mensagem correspondente
		// Vamos percorrer o e (que é o MethodArgumentNotValidException que foi declarado no argumento)
		// E para cada erro vamos gerar o objeto FieldMessage
		
		for (FieldError fe : e.getBindingResult().getFieldErrors()) {
			err.addError(fe.getField(), fe.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
