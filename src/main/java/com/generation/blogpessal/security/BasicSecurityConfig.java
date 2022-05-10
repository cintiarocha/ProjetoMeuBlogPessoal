package com.generation.blogpessal.security;

import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// notação de security 
// definindo as maneiras pra conseguir entrar na api
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// Serve para comparar os dados digitados com os dados salvos no banco de dados
	/* Serve para validar os dados digitados, comparando-os com os dados 
	salvos do banco de dados, que a UserDetailsService cuida. */
	@Autowired
	private UserDefinedFileAttributeView userDetailsService;
	
	//Criado uma exceção 
	// Metodo para autenticar , atraves do usuario em memoria - só pra testar 
	// Pra poder entrar com um usuário em memória. Checa no banco de dados, não acha 
	// aí tem a exceção em baixo (Um usuário para testar a API mais rápido), sem 
	// validação de token pra agilizar testes no processo de desenvolvimento. 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.inMemoryAuthentication()
		.withUser("root") // Checa se o usuário é root, aí pula pra senha
		.password(passwordEncoder().encode("root"))// Checa se a senha é root
		.authorities("ROLE_USER"); /* Valida que é um usuário, necessário para saber que 
		  * tipo de usuário que é. Se não tiver, não terá permissões 
		  * de CRUD de um usuário normal e válido */
	}
		//notação que deixa uma função acessivel globalmente(em toda a minha aplicação)
				@Bean
		//criptografa a senha
				public PasswordEncoder passwordEncoder() { 
					return new BCryptPasswordEncoder();
				}
				
				//Criar uma exceção
				@Override
				protected void configure(HttpSecurity http) throws Exception{
												
				http.authorizeRequests()
				.antMatchers("/usuarios/logar").permitAll() // De qualquer lugar, você terá acesso a login
				.antMatchers("/usuarios/cadastrar").permitAll() // e cadastro já que as rotas estão abertas
				.antMatchers(HttpMethod.OPTIONS).permitAll() /* Permite que as rotas estejam acessíveis com GET
				 											  * Permite saber quais métodos estão abertos na
				 											  * documentação da API e que estão abertos nela 
				 											  * e é possível utilizar eles. */
				.anyRequest().authenticated() // Para outras requisições, tem que está ou cadastrado ou em memória
				.and().httpBasic() // HttpBasic = CRUD | Define que só será aceito métodos CRUD
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
				/* ^ Define que toda requisição tem começo, meio e fim. Uma por vez e ajuda a prevenir ataques 
				 * cibernéticos e invasões com várias requisições de uma forma | Tipo quando expira o token 
				 * em um site como na plataforma da Generation Brasil. */
				.and().cors() /* Funciona como o '@CrossOrigins', vendo de qual porta está vindo a requisição e
				liberando acesso para todas (Do Front-end pro Back-end basicamente) */
				.and().csrf().disable(); // Autoriza PUT e DELETE na requisição

				}

		
}
