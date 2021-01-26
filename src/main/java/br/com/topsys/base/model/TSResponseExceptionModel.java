package br.com.topsys.base.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TSResponseExceptionModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1756968031618713706L;
	private int codigo;
	private Date data;
	private String mensagem;
	
}   