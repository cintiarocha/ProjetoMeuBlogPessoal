package com.generation.blogpessal.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.blogpessal.Repository.UsuarioRepository;
import com.generation.blogpessal.model.Usuario;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

//'String userName' seria o parâmetro e-mail, como 'rafaelballabi@hotmail.com'
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		Optional<Usuario> usuario = userRepository.findByUsuario(userName);
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + "Usuário não encontrado | " + "User not found")); 
																		  
		// ^ Só conseguimos usar ele por causa do Optional (O 'throw')
		return usuario.map(UserDetailsImpl :: new).get(); // Se encontrar o usuário, vai passar pela validação
}
}