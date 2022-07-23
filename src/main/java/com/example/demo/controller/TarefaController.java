package com.example.demo.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.demo.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.bo.event.RecursoCriadoEvent;
import com.example.demo.model.bo.exceptionhandler.ApplicationExceptionHandler.Erro;
import com.example.demo.model.dao.tarefa.TarefaDAO;
import com.example.demo.model.bo.LancamentoBO;
import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
	
	@Autowired
	private TarefaDAO tarefaDAO;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LancamentoBO lancamentoService;
	
	@Autowired
	private MessageSource messageSource;
	
//	@GetMapping
//	public Page<Tarefa> pesquisar(TarefaFilter tarefaFilter, Pageable pageable){
//		return tarefaRepository.filtrar(tarefaFilter, pageable);
//		}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Tarefa>> buscarPorLancamento(@PathVariable Long codigo){
		Optional<Tarefa> lancamento = tarefaDAO.findById(codigo);
		return !lancamento.isEmpty() ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa tarefa, HttpServletResponse response) throws PessoaInexistenteOuInativaException{
		Tarefa tarefaSalvo = lancamentoService.salvar(tarefa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tarefaSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		tarefaDAO.deleteById(codigo);
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}
