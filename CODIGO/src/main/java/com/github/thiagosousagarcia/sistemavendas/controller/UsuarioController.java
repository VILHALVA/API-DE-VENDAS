package com.github.thiagosousagarcia.sistemavendas.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.CredenciaisDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.TokenDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.UsuarioDTO;
import com.github.thiagosousagarcia.sistemavendas.excpetion.InvalidPasswordException;
import com.github.thiagosousagarcia.sistemavendas.model.Usuario;
import com.github.thiagosousagarcia.sistemavendas.security.jwt.JwtService;
import com.github.thiagosousagarcia.sistemavendas.security.service.UserService;
import com.github.thiagosousagarcia.sistemavendas.service.UsuarioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@ApiOperation("Cadastra um novo usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuário cadastrado com sucesso")
	})
	@PostMapping
	public ResponseEntity<UsuarioDTO> create (@RequestBody @Valid UsuarioDTO usuarioDTO, final UriComponentsBuilder uriBuilder){
		Usuario novoUsuario = this.usuarioService.salvar(usuarioDTO.toEntity());
		
		UsuarioDTO novoUsuarioDTO = novoUsuario.toDTO();
		final Long id = novoUsuarioDTO.getId();
		final URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body(novoUsuarioDTO);
	}
	
	@ApiOperation("Autentica um usuário")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuário autenticado com sucesso"),
		@ApiResponse(code = 401, message = "Senha inválida")
	})
	@PostMapping("/auth")
	public ResponseEntity<TokenDTO> autenticate(@RequestBody CredenciaisDTO credenciaisDTO) {
		try {
			Usuario usuario = Usuario.builder()
					.login(credenciaisDTO.getLogin())
					.senha(credenciaisDTO.getSenha()).build();
			userService.autenticate(usuario);
			String token = jwtService.generateToken(usuario);
			TokenDTO tokenDTO = new TokenDTO(usuario.getLogin(), token);
			
			return ResponseEntity.ok(tokenDTO);
			
		} catch (UsernameNotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
	
	@ApiOperation("Busca todos os usuários cadastrados")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuários carregados com sucesso")
	})
	@GetMapping
	public Page<UsuarioDTO> findAll(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		Page<Usuario> usuarios = this.usuarioService.findAll(pageable);
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		
		if(usuarios != null) {
			usuariosDTO = usuarios.getContent().stream().map(Usuario::toDTO).collect(Collectors.toList());
		}
		
		return new PageImpl<UsuarioDTO>(usuariosDTO, pageable, usuariosDTO.size());
	}
	
}
