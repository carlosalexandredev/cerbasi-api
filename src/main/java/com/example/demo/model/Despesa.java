package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
	@Size(max = 12)
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
