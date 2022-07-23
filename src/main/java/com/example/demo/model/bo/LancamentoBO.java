package com.example.demo.model.bo;

import com.example.demo.model.Tarefa;
import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.model.dao.tarefa.TarefaDAO;
import com.example.demo.model.dao.pessoa.PessoaDAO;

@Service
public class LancamentoBO {
	
	@Autowired
	private PessoaDAO pessoaRepository;
	
	@Autowired
	private TarefaDAO tarefaDAO;

	public Tarefa salvar(Tarefa tarefa) throws PessoaInexistenteOuInativaException {
		Usuario usuario = pessoaRepository.getById(tarefa.getUsuario().getCodigo());
		if(usuario == null || !usuario.isAtivo()){
			throw new PessoaInexistenteOuInativaException();
		}
		return tarefaDAO.save(tarefa);
	}
		
}
