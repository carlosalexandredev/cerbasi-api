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
import com.example.demo.model.Despesa;
import com.example.demo.repository.DespesaRepository;

@RestController  
@RequestMapping("/despesa") 
public class DespesaResource {

	@Autowired 
	private DespesaRepository despesaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//TODO: Method don't return the handler "Recurso nao encontrado" when inserting a value whit don't exists in the DataBase.
	@GetMapping
	public List<Despesa> listar(){
		return despesaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Despesa> criar(@Valid @RequestBody Despesa despesa, HttpServletResponse response) {
		Despesa despesaSalvo = despesaRepository.save(despesa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, despesaSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(despesaSalvo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarCategoria(@PathVariable Long codigo){
		Optional<Despesa> despesa = despesaRepository.findById(codigo);
		return !despesa.isEmpty() ? ResponseEntity.ok(despesa) : ResponseEntity.notFound().build();
	}
}
