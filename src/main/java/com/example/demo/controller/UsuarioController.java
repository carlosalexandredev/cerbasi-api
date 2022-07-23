package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.demo.model.Usuario;
import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dto.usuario.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.bo.event.RecursoCriadoEvent;
import com.example.demo.model.dao.pessoa.PessoaDAO;
import com.example.demo.model.bo.UsuarioBO;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private PessoaDAO pessoaRepository;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar(){
		List<UsuarioDTO> usuarios = usuarioBO.buscaAll();
		return !usuarios.isEmpty() ?  ResponseEntity.ok(usuarios) : ResponseEntity.noContent().build();
	}
	 
	//TODO: Method don't return the handler "Recurso nao encontrado" when inserting a value whit don't exists in the DataBase.
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<UsuarioDTO>> buscarPelaPessoa(@PathVariable Long codigo) throws PessoaInexistenteOuInativaException {
		Optional<UsuarioDTO> usuario = usuarioBO.buscaById(codigo);
		return !usuario.isEmpty() ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalva = pessoaRepository.save(usuario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalva);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario){
		Usuario usuarioSalva = usuarioBO.atualizar(codigo, usuario);
		return ResponseEntity.ok(usuarioSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
		usuarioBO.atualizarPropriedadeAtiva(codigo, ativo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pessoaRepository.deleteById(codigo);
	}
}
	
	
	

