package br.com.allan.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.allan.desafio.model.Breed;
import br.com.allan.desafio.model.Dog;
import br.com.allan.desafio.repository.BreedRepository;
import br.com.allan.desafio.repository.DogRepository;

@Controller
public class DogController {
	
	@Autowired
	DogRepository dogRepository;
	
	@Autowired
	BreedRepository breedRepository;
	
	//Index page
	@RequestMapping("/")
	public String index(Model model) {
		
		populatePage(model);
		
		return "index";
	}
	
	//Action for saving the page
	@RequestMapping(value= "save", method = RequestMethod.POST)
	public String save(@RequestParam("name") String dogName, @RequestParam("breed") String breedName, Model model){
		
		saveDog(dogName, breedName, model);
		
		populatePage(model);
		
		return "index";

	}	
	
	private void populatePage(Model model) {
		
		Iterable<Dog> dogs = dogRepository.findAll();
		Iterable<Breed> breeds = breedRepository.findAll();
		
		model.addAttribute("dogs", dogs);
		model.addAttribute("breeds",breeds);
	}
	
	private void saveDog(String dogName, String breedName, Model model) {
		
		Breed breed = new Breed(breedName);
		
		List<Breed> breedList = breedRepository.findByName(breedName);
		
		if (breedList.isEmpty()) {
			breedRepository.save(breed);
		} else {
			breed = breedList.get(0);
		}
		
		Dog dog = new Dog(dogName, breed);
		
		List<Dog> dogList = dogRepository.findByName(dog.getName());
		
		if (dogList.isEmpty()) {

			dogRepository.save(dog);
		}
	}
	
}
