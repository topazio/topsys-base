package br.com.topsys.base.exception;

public class TSSystemException extends RuntimeException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7047729572107050553L;
	
	public TSSystemException(Exception e) {
		super(e);
	} 


	public TSSystemException(String mensagem) {
		super(mensagem);
	}
	
	
	public TSSystemException(String mensagem,Exception e) {
		super(mensagem,e);
	}
	
	
}
