package com.example.demo.model.dao.pessoa;

import com.example.demo.model.Usuario;
import com.example.demo.model.dto.PessoaDTO_;
import com.example.demo.model.dto.usuario.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaDAO extends JpaRepository<Usuario, Long>{
}
