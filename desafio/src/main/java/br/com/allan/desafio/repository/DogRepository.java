package br.com.allan.desafio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.allan.desafio.model.Dog;

public interface DogRepository extends CrudRepository<Dog, Long> {
	
	List<Dog> findByName(String name);
}
