package com.example.demo.model.dto.tarefas;

import com.example.demo.model.dto.enuns.TipoLancamento;
import com.example.demo.model.dto.usuario.ResponseUsuarioDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class RequestTarefasDTO {
	private Long codigo;
	
	@NotNull
	@Size(max = 50)
	private String descricao;
	
	@NotNull
	private LocalDate dataVencimento;

	private LocalDate dataPagamento;
	
	@NotNull
	private BigDecimal valor;
	
	@Size(max = 100)
	private String observacao;
	
	@NotNull
	private TipoLancamento tipo;
	
	@NotNull
	private ResponseUsuarioDTO pessoa;
	
}
