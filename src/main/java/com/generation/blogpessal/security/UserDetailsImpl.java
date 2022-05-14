package com.generation.blogpessal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.blogpessal.model.Usuario;




public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	// metodos padroes do security 
	//autoriza todos os privilegios de usuario
	private List<GrantedAuthority> authorities;
	
	public UserDetailsImpl (Usuario usuario) {
		this.userName = usuario.getUsuario();
		this.password = usuario.getSenha();
	}
	//Passando os privilegios pro usuario , é padrão para aplicação
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {

		return userName;
	}

	// metodo padrão
	// se a conta do usuario expirou 
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	// ver se a conta está bloqueada
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	// se a crendicial da conta não está expirada
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	// se a conta esta habilitada ou não
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}




}
