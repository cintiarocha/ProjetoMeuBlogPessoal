package com.generation.blogpessal.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessal.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	// Optional: É do JAVA mesmo,quando não se sabe qual tipo de resposta pode ter ao buscar na requisição e tem mais de uma possível
		// Basicamente que nem o List (Mas este só tem duas respostas, o Optional várias)
	public Optional<Usuario> findByUsuario(String usuario);

	public static List<Usuario> findAllByNomeContainingIgnoreCase(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	}

	
	

	


