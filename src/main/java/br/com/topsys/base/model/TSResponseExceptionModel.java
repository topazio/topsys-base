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
	private int codigo;
	private Date data;
	private String mensagem;
	
}   