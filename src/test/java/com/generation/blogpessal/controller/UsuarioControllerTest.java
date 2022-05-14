package com.generation.blogpessal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.blogpessal.model.Usuario;
import com.generation.blogpessal.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //indica que a porta 8080 já está ocupada.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test //cria um usuario
	@Order(1)//metodo executado primeiro
	@DisplayName("Cadastrar Um Usuário")
	public void deveCriarUmUsuario() {
	
	HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, "Jackie","jackietequila@gmail.com","cachorro", null));
	
	ResponseEntity<Usuario> resposta = testRestTemplate 
			.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
	
	assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
	assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
	
	}
	
	@Test
	@Order(2)
	@DisplayName("naoDeveDuplicarUsuario")
	public void naoDeveDuplicarUsuario() {
		
		usuarioService.cadastraUsuario(new Usuario(0L,"lupita", "lupita@gmail.com", "gato", null));
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L,"lupita", "lupita@gmail.com", "gato", null));
		
		ResponseEntity<Usuario> resposta = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
			
	}
	
	@Test
	@Order(3)
	@DisplayName("Atualizar um Usuario")
	public void deveAtualizarUmUsuario() {
		
		Optional<Usuario> usuarioCreate = usuarioService.cadastraUsuario(new Usuario(0L,"Nora","nora@gmail.com", "gato",null));
		
		Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(),"Madame Nora", "nora@gmail.com","gato", null);
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);
		
		ResponseEntity<Usuario> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuarios/atualizar", HttpMethod.PUT, requisicao, Usuario.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(usuarioUpdate.getNome(),resposta.getBody().getNome());
		assertEquals(usuarioUpdate.getUsuario(), resposta.getBody().getUsuario());
		
			}
	@Test
	@Order(4)
	@DisplayName("Listar todos os Usuarios")
	public void deveMostrarTodosUsuarios( ) {
		
		usuarioService.cadastraUsuario(new Usuario (0L, "pitty", "pitty@gmail.com","cachorro",null));
		
		usuarioService.cadastraUsuario(new Usuario (0L, "Fred","fred@gmail.com", "cachorro", null));
		
		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuario,all", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		
		
	}

}
 