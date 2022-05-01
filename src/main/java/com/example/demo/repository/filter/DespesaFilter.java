package com.example.demo.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.model.TipoDespesa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DespesaFilter {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDespesa;

	private TipoDespesa tipo;
}
