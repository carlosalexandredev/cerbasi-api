package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Lancamento;
import com.example.demo.model.Pessoa;
import com.example.demo.repository.LancamentoRepository;
import com.example.demo.repository.PessoaRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(Lancamento lancamento) throws PessoaInexistenteOuInativaException{
		Pessoa pessoa = pessoaRepository.getById(lancamento.getPessoa().getCodigo());
		if(pessoa == null || !pessoa.isAtivo()){
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
	}
		
}
