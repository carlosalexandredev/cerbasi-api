package com.example.demo.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.event.RecursoCriadoEvent;
import com.example.demo.model.Investimento;
import com.example.demo.repository.InvestimentoRepository;

@RestController  
@RequestMapping("/investimento") 
public class InvestimentoResource {

	@Autowired 
	private InvestimentoRepository InvestimentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//TODO: Method don't return the handler "Recurso nao encontrado" when inserting a value whit don't exists in the DataBase.
	@GetMapping
	public List<Investimento> listar(){
		return investimentoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Investimento> criar(@Valid @RequestBody Investimento investimento, HttpServletResponse response) {
		Investimento investimentoSalvo = investimentoRepository.save(investimento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, investimentoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(investimentoSalvo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarCategoria(@PathVariable Long codigo){
		Optional<Investimento> investimento = investimentoRepository.findById(codigo);
		return !investimento.isEmpty() ? ResponseEntity.ok(investimento) : ResponseEntity.notFound().build();
	}
}
