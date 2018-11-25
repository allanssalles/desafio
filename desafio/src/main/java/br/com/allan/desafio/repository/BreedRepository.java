package br.com.allan.desafio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.allan.desafio.model.Breed;

public interface BreedRepository extends CrudRepository<Breed, Long> {
	
	List<Breed> findByName(String name);
}
