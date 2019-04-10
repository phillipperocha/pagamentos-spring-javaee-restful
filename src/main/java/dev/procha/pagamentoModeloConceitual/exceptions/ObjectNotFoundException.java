package dev.procha.pagamentoModeloConceitual.exceptions;

// Vamos extender de RunTimeException, para que a ObjectNotFoundException seja uma 
// unchecked exception. Ou seja, ela não obriga ser tratada.
public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
