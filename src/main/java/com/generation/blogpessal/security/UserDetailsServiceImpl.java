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

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> usuario = userRepository.findByUsuario(userName);
	
		usuario.orElseThrow(()-> new UsernameNotFoundException(userName + "Usuario não encontrado"));
		
		//atribui o resultado encontrado no optional para alimentar o UserDetailsImpl
		return usuario.map(UserDetailsImpl::new).get();
	}

}
