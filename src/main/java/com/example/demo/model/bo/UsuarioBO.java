package com.example.demo.model.bo;

import com.example.demo.model.Usuario;
import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dto.usuario.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.pessoa.PessoaDAO;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioBO {
	
	@Autowired
	private PessoaDAO pessoaRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<UsuarioDTO> buscaAll(){
		List<Usuario> usuarios = Optional.ofNullable(pessoaRepository.findAll();

		return preencheListaUsuarioDTO(usuarios);
	}

	public Optional<UsuarioDTO> buscaById(Long codigo) throws PessoaInexistenteOuInativaException {
		Optional<Usuario> usuario = Optional.ofNullable(pessoaRepository.findById(codigo)
				.orElseThrow(PessoaInexistenteOuInativaException::new));

		return Optional.of(preencheUsuarioDTO(usuario.get()));
	}

	public UsuarioDTO criarUser(UsuarioDTO usuario){
//		Usuario usuarioSalva = pessoaRepository.save();

		return
	}

	private UsuarioDTO preencheUsuarioDTO(Usuario user) {
		return UsuarioDTO.builder()
				.nome(user.getNome())
				.equipe(user.getEquipe())
				.funcao(user.getFuncao())
				.codigo(user.getCodigo())
				.email(user.getEmail())
				.privilegio(user.getPrivilegio())
				.ativo(user.isAtivo())
				.build();
	}

	private List<UsuarioDTO> preencheListaUsuarioDTO(List<Usuario> usuarios){
		List<UsuarioDTO> usuariosListDTO = new ArrayList<>();
		for (Usuario user: usuarios) {
			UsuarioDTO usuariosDTO = UsuarioDTO.builder()
					.nome(user.getNome())
					.equipe(user.getEquipe())
					.funcao(user.getFuncao())
					.codigo(user.getCodigo())
					.email(user.getEmail())
					.privilegio(user.getPrivilegio())
					.ativo(user.isAtivo())
					.build();

			usuariosListDTO.add(usuariosDTO);
		}

		return usuariosListDTO;
	}
	public Usuario atualizar (Long codigo, Usuario usuario) {
		Usuario usuarioSalva = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(usuario, usuarioSalva, "codigo");
		return pessoaRepository.save(usuarioSalva);
	}

	public void atualizarPropriedadeAtiva(Long codigo, Boolean ativo){
		Usuario usuarioSalva = buscarPessoaPeloCodigo(codigo);
		usuarioSalva.setAtivo(ativo);
		pessoaRepository.save(usuarioSalva);
	}
	
	public Usuario buscarPessoaPeloCodigo(Long codigo) {
		Usuario usuarioSalva =  pessoaRepository.findById(codigo).orElse(null);
		if(usuarioSalva == null){throw new EmptyResultDataAccessException(1);}
		return usuarioSalva;
	}
}
