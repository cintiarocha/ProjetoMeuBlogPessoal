package com.generation.blogpessal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.generation.blogpessal.model.TemaModel;
@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long> {
	public List<Model> findAllByDescricaoContainingIgnoreCase(String descricao);
	

	
}
