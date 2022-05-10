package com.generation.blogpessal.Controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessal.Repository.TemaRepository;
import com.generation.blogpessal.model.TemaModel;

@RestController
@RequestMapping("/tema")
@CrossOrigin("*")
public class TemaController {
	
	

		// tranfere a responsabilidade de contruir as consultas no banco de dados para o
		// repository
		@Autowired
		private TemaRepository repository;

		@GetMapping
		public List<TemaModel> getAll() {
			return repository.findAll();
		}

		@PostMapping
		public ResponseEntity<TemaModel> postTema(@Valid @RequestBody TemaModel tema) {
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
		}
	}
	