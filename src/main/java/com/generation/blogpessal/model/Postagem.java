package com.generation.blogpessal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//transforma o objeto criado em uma tabela no banco de dados
@Entity
//dá um nome para a tabela no meu banco de dados
@Table(name = "postagem")

public class Postagem {
	
	// define a coluna de id como chave primaria
	@Id
	// equivalente ao auto_increment no mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// define que o campo é obrigatório
	@NonNull
	@Size(min = 5, max = 100)
	private String titulo;
	
	// define um numero minimo e maximo de caracteres no campo 
	@NotNull
	@Size(min = 10, max = 500)
	private String texto;
	
	@UpdateTimestamp
	private Date date = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JsonIgnoreProperties ("postagem")
	private  TemaModel tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;



	
	

	public TemaModel getTema() {
		return tema;
	}

	public void setTema(TemaModel tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	} 

}
