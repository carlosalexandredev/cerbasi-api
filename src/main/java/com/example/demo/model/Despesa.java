package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "despesa")
public class Despesa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Size(min = 3, max = 30)
	private String nome;

	@NotNull
	@Size(size = 12)
	private BigDecimal valor;

	@NotNull
	@Column(name = "data_despesa")
	private LocalDate dataInvestimento;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoDespesa tipo;

	@NotNull
	@ManyToOne
	@JoinColumn(name= "codigo_pessoa")
	private Pessoa pessoa;
}
