package com.generation.blogpessal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessal.Repository.UsuarioRepository;
import com.generation.blogpessal.model.Usuario;

//indicando que a classe UsuarioRepositoryTest é uma classe de test, que vai rodar em uma porta aleatoria a cada teste realizado
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//cria uma instancia de testes, que define que o ciclo de vida do teste vai respeitar o ciclo de vida da classe(será executado e resetado após o uso)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsuarioRespositoryTest {
	
	@Autowired
	private UsuarioRepository repository;
	
	@BeforeAll
	void start() {
		
		repository.save(new Usuario(0L, "Maiar", "isadora@gmail.com","51 e pinga","https://i.imgur.com/FETvs2O.jpg"));
		
		repository.save(new Usuario(0L, "Michael", "michaeltrimundial@gmail.com","nunca fui rebaixado","https://i.imgur.com/FETvs2O.jpg"));
		
		repository.save(new Usuario(0L, "Brocco", "broco@gmail.com","broccolis","https://i.imgur.com/FETvs2O.jpg"));
		
		repository.save(new Usuario(0L, "Mayara", "will31smith@gmail.com","cenoura","https://i.imgur.com/FETvs2O.jpg"));
	}
	@Test
	@DisplayName("Teste que retorna 1 usuario") //tem que retornar só um usuario
	public void retornaUmUsuario() {
		Optional<Usuario> usuario = repository.findByUsuario("isadora@gmail.com");//testar dentro do repository com o findByUsuario pra procurar o usuario
		assertTrue(usuario.get().getUsuario().equals("isadora@gmail.com"));//para retornar um true ou false, ele compara pra ver se é igual ao que eu to procurando
	}
	//deleta todos os usuarios teste cadastrados 
	@AfterAll
	public void end() {
		repository.deleteAll();
	}
	
	@Test
	@DisplayName("Retornar 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = UsuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Maria da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Joana da Silva"));

	}
	

		
	}


	
	