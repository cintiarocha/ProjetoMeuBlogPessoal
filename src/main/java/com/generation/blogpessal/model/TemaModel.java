package com.generation.blogpessal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table (name = "tb_tema")
public class TemaModel {
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String descricao;
	
	// relacionamento do tipo um pra muitos (um tema para varias postagens)
	//o cascade serve para que todas as atualizações ou deleções de um tema, afetem todas as postagens atrelhadas a ele 
	@OneToMany(mappedBy = "tema",cascade = CascadeType.ALL)
	@JsonIgnoreProperties ("tema")
	private List<Postagem> postagem;
	

}
