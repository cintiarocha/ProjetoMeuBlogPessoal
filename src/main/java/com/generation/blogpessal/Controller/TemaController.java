package com.generation.blogpessal.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		@PutMapping
		public ResponseEntity<TemaModel> put(@RequestBody TemaModel tema){
			return repository.findById(tema.getId())
					.map(resposta -> ResponseEntity.ok().body(repository.save(tema)))
							.orElse(ResponseEntity.notFound().build());
		}
<<<<<<< HEAD
		@PutMapping
		public ResponseEntity<TemaModel> put(@RequestBody TemaModel tema){
			return repository.findById(tema.getId())
					.map(resposta -> ResponseEntity.ok().body(repository.save(tema)))
							.orElse(ResponseEntity.notFound().build());
		}
=======
>>>>>>> 64d794a3c8920312bc6e7ac01b228217940e2bde
		//verificação antes de deletar por id
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deletePostagem(@PathVariable Long id) {
			
			return repository.findById(id)
					.map(resposta -> {
						repository.deleteById(id);
						return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
					.orElse(ResponseEntity.notFound().build());
	}
}
<<<<<<< HEAD
	
=======
	
	
>>>>>>> 64d794a3c8920312bc6e7ac01b228217940e2bde
