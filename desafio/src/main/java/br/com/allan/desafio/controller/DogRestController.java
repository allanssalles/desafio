package br.com.allan.desafio.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.allan.desafio.model.Breed;
import br.com.allan.desafio.model.Dog;
import br.com.allan.desafio.repository.BreedRepository;
import br.com.allan.desafio.repository.DogRepository;

@RestController
public class DogRestController {
	
	@Autowired
	DogRepository dogRepository;
	
	@Autowired
	BreedRepository breedRepository;
	
	// Método genérico html
	/*@RequestMapping("/dog")
	public Dog dog(@RequestParam(value="name") String name, @RequestParam(value="race") String race) {
        
		Dog dog = new Dog(name,race);
		dogRepository.save(dog);
		
		return dog;
    }*/

	@GetMapping("/dog")
	public Iterable<Dog> dogs() {
        
		Iterable<Dog> dogs =  dogRepository.findAll();
		
		return dogs;
    }
	
	@PostMapping(path = "/dog",consumes = "application/json", produces = "application/json")
	public Dog postDog(@RequestBody String jsonStringDog) {
		
		JSONObject jsonDog= new JSONObject(jsonStringDog);
		String breedName = jsonDog.getString("breed");
		String dogName = jsonDog.getString("dog");
		
		Breed breed = new Breed(breedName);
		
		List<Breed> breedList = breedRepository.findByName(breedName);
		
		if (breedList.isEmpty()) {
			breedRepository.save(breed);
		}else {
			breed = breedList.get(0);
		}
		
		Dog dog = new Dog(dogName, breed);
		
		List<Dog> dogs = dogRepository.findByName(dogName);
		
		if (dogs != null && dogs.size() > 0 ) {
			
			dog = dogs.get(0);
		} else {
			
			dogRepository.save(dog);
		}
			
		return dog;
    }

}
