package com.example.demo.model;

import javax.persistence.Embeddable;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class Endereco {
	
	@Size(max = 30)
	private String logradouro;
	
	private int numero;
	
	@Size(max = 100)
	private String complemento;
	
	@Size(max = 30)
	private String bairro;
	
	@Size(max = 9)
	private String cep;
	
	@Size(max = 30)
	private String cidade;
	
	@Size(max = 2)
	private String estado;

}
