package dev.procha.pagamentoModeloConceitual.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	// o status http do erro
	private Integer status;
	// a mensagem de erro
	private String msg;
	// o instante em que ocorreu, será um long representando em milisegundos
	private Long timeStamp;
	
	public StandardError() {
		// TODO Auto-generated constructor stub
	}
	
	public StandardError(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
