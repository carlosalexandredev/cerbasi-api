package com.example.demo.model.dto.usuario;

import com.example.demo.model.dto.enuns.TipoUsuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "pessoa" )
public class RequestUsuarioDTO {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Size(max = 80)
	private String nome;
	
	@NotNull
	private boolean ativo;

	@NotNull
	private TipoUsuario privilegio;
	
	@Embedded
	private EnderecoDTO endereco;

}
