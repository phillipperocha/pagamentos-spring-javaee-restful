package dev.procha.pagamentoModeloConceitual.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		
	}

	// Aqui é essa palavra que vai ser convertida no Json, antes aparecia Lists: e agora aparece Errors:
	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	// Eu não quero ficar adicionando uma lista de errors aqui
	// Então vamos trocar esse setList por um addError para adicionar de erro em erro
	//public void setList(List<FieldMessage> list) {
	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
	
}
